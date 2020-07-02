/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
 
/**
 * @file TablestorageTable.java
 * @date 2019/02/26 15:15:01
 * @brief 
 */

package com.baidubce.services.tablestoragehbaseclient.hbase;

import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageResultScanner;
import com.baidubce.services.tablestoragehbaseclient.adaptor.Constants;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TableStorageAdaptor;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConfiguration;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.Service;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.AbstractClientScanner;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.RowMutations;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.ipc.CoprocessorRpcChannel;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TablestorageTable derived from hbase.client.Table, Used to communicate with a single TableStorage table.
 * Obtain an instance from a Connection and call close() afterwards.
 *
 * @date 2019/02/26 16:18:07
 */
public class TablestorageTable implements Table {
    private final TableName tableName;
    private TablestorageConnection connection;
    private TableStorageAdaptor adaptor;
    private ExecutorService pool;
    private volatile long writeBufferSize = 0;

    /**
     * Construct a TableStorage Table with target table name.
     *
     * @param connection the connection used to construct this TableStorageTable.
     * @param tableName the name of target table in TableStorage.
     */
    public TablestorageTable(TablestorageConnection connection, TableName tableName) {
        this.connection = connection;
        this.tableName = tableName;
        this.pool = Executors.newCachedThreadPool();

        TablestorageConfiguration conf = connection.getTablestorageConfiguration();
        this.adaptor = new TableStorageAdaptor(conf.getEndpoint(), conf.getInstanceName(),
                conf.getAccessKeyId(), conf.getSecretAccessKey());
    }

    @Override
    public TableName getName() {
        return this.tableName;
    }

    @Override
    public Configuration getConfiguration() {
        return this.connection.getConfiguration();
    }

    @Override
    public HTableDescriptor getTableDescriptor() throws IOException {
        return adaptor.getTable(tableName.getNameAsString());
    }

    @Override
    public boolean exists(Get get) throws IOException {
        Result result = get(get);
        return !result.isEmpty();
    }

    @Override
    public boolean[] existsAll(List<Get> gets) throws IOException {
        Result[] results = get(gets);
        boolean[] exists = new boolean[gets.size()];
        int index = 0;
        for (Result result : results) {
            exists[index++] = !result.isEmpty();
        }
        return exists;
    }

    @Override
    public void batch(final List<? extends Row> actions, final Object[] results)
            throws IOException, InterruptedException {
        Object[] objects = adaptor.batch(tableName.getNameAsString(), actions);
        for (int i = 0; i < objects.length; i++) {
            results[i] = objects[i];
        }
    }

    @Deprecated
    @Override
    public Object[] batch(final List<? extends Row> actions)  throws IOException, InterruptedException {
        Object[] objects = new Object[actions.size()];
        batch(actions, objects);
        return objects;
    }

    @Override
    public <R> void batchCallback(final List<? extends Row> actions, final Object[] results,
                                  final Batch.Callback<R> callback) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("batchCallback(final List<? extends Row> actions, "
                + "final Object[] results, final Batch.Callback<R> callback)");
    }

    @Deprecated
    @Override
    public <R> Object[] batchCallback(List<? extends Row> actions, Batch.Callback<R> callback)
            throws IOException, InterruptedException {
        // do nothing
        throw new UnsupportedOperationException(
                "batchCallback(List<? extends Row> actions, Batch.Callback<R> callback)");
    }

    @Override
    public Result get(Get get) throws IOException {
        checkNotNull(get, "get should not be null");

        return adaptor.getRow(tableName.getNameAsString(), get);
    }

    @Override
    public Result[] get(List<Get> gets) throws IOException {
        checkNotNull(gets, "gets should not be null");

        if (gets.size() == 0) {
            return new Result[0];
        }
        return adaptor.batchGetRow(tableName.getNameAsString(), gets);
    }

    @Override
    public ResultScanner getScanner(Scan scan) throws IOException {
        return new Scanner(adaptor, pool, scan, tableName.getNameAsString());
    }

    @Override
    public ResultScanner getScanner(byte[] family) throws IOException {
        if (Bytes.compareTo(family, Bytes.toBytes(Constants.DEFAULT_FAMILY)) != 0) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }

        Scan scan = new Scan();
        scan.addFamily(family);
        return new Scanner(adaptor, pool, scan, tableName.getNameAsString());
    }

    @Override
    public ResultScanner getScanner(byte[] family, byte[] qualifier) throws IOException {
        if (Bytes.compareTo(family, Bytes.toBytes(Constants.DEFAULT_FAMILY)) != 0) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }

        Scan scan = new Scan();
        scan.addColumn(family, qualifier);
        return new Scanner(adaptor, pool, scan, tableName.getNameAsString());
    }

    public class Scanner extends AbstractClientScanner {
        private TablestorageResultScanner scanner;

        public Scanner(TableStorageAdaptor adaptor, ExecutorService pool, Scan scan,
                       String tableName) {
            scanner = new TablestorageResultScanner(adaptor, pool, scan, tableName);
        }

        @Override
        public Result next() throws IOException {
            return scanner.next();
        }

        @Override
        public Result[] next(int nbRows) throws IOException {
            List<Result> resultList = new ArrayList<Result>(nbRows);
            for (int i = 0; i < nbRows; i++) {
                Result result = next();
                if (result == null) {
                    break;
                }

                resultList.add(result);
            }

            Result[] results = new Result[resultList.size()];
            return resultList.toArray(results);
        }

        @Override
        public void close() {
            scanner.close();
        }

        @Override
        public boolean renewLease() {
            throw new UnsupportedOperationException("ResultScanner.renewLease()");
        }
    }

    @Override
    public void put(Put put) throws IOException {
        checkNotNull(put, "put should not be null");

        adaptor.putRow(tableName.getNameAsString(), put);
    }

    @Override
    public void put(List<Put> puts) throws IOException {
        checkNotNull(puts, "puts should not be null");

        if (puts.size() == 0) {
            return;
        }
        adaptor.batchPutRow(tableName.getNameAsString(), puts);
    }

    @Override
    public boolean checkAndPut(byte[] row, byte[] family, byte[] qualifier, byte[] value, Put put) throws IOException {
        // not support
        throw new UnsupportedOperationException(
                "checkAndPut(byte[] row, byte[] family, byte[] qualifier, byte[] value, Put put)");
    }

    @Override
    public boolean checkAndPut(byte[] row, byte[] family, byte[] qualifier,
            CompareFilter.CompareOp compareOp, byte[] value, Put put) throws IOException {
        // not support
        throw new UnsupportedOperationException("checkAndPut(byte[] row, byte[] family, byte[] qualifier, "
                + "CompareFilter.CompareOp compareOp, byte[] value, Put put)");
    }

    @Override
    public void delete(Delete delete) throws IOException {
        checkNotNull(delete, "delete should not be null");

        adaptor.deleteRow(tableName.getNameAsString(), delete);
    }

    @Override
    public void delete(List<Delete> deletes) throws IOException {
        checkNotNull(deletes, "deletes should not be null");

        if (deletes.size() == 0) {
            return;
        }
        adaptor.batchDeleteRow(tableName.getNameAsString(), deletes);
    }

    @Override
    public boolean checkAndDelete(byte[] row, byte[] family, byte[] qualifier, byte[] value, Delete delete)
            throws IOException {
        // not support
        throw new UnsupportedOperationException(
                "checkAndDelete(byte[] row, byte[] family, byte[] qualifier, byte[] value, Delete delete)");
    }

    @Override
    public boolean checkAndDelete(byte[] row, byte[] family, byte[] qualifier,
            CompareFilter.CompareOp compareOp, byte[] value, Delete delete) throws IOException {
        // not support
        throw new UnsupportedOperationException("checkAndDelete(byte[] row, byte[] family, byte[] qualifier, "
                + "CompareFilter.CompareOp compareOp, byte[] value, Delete delete)");
    }

    @Override
    public void mutateRow(final RowMutations rm) throws IOException {
        throw new UnsupportedOperationException("mutateRow(final RowMutations rm)");
    }

    @Override
    public Result append(final Append append) throws IOException {
        throw new UnsupportedOperationException("append(final Append append)");
    }

    @Override
    public Result increment(final Increment increment) throws IOException {
        throw new UnsupportedOperationException("increment(final Increment increment)");
    }

    @Override
    public long incrementColumnValue(byte[] row, byte[] family, byte[] qualifier, long amount) throws IOException {
        throw new UnsupportedOperationException(
                "incrementColumnValue(byte[] row, byte[] family, byte[] qualifier, long amount)");
    }

    @Override
    public long incrementColumnValue(byte[] row, byte[] family, byte[] qualifier, long amount, Durability durability)
            throws IOException {
        throw new UnsupportedOperationException("incrementColumnValue(byte[] row, byte[] family, byte[] qualifier, "
                + "long amount, Durability durability)");
    }

    @Override
    public void close() throws IOException {
        pool.shutdown();
        adaptor.close();
    }

    @Override
    public CoprocessorRpcChannel coprocessorService(byte[] row) {
        // not support
        throw new UnsupportedOperationException("coprocessorService(byte[] row)");
    }

    @Override
    public <T extends Service, R> Map<byte[], R> coprocessorService(final Class<T> service, byte[] startKey,
                                                                    byte[] endKey, final Batch.Call<T, R> callable)
            throws ServiceException, Throwable {
        throw new UnsupportedOperationException("coprocessorService(final Class<T> service, byte[] startKey, "
                + "byte[] endKey, final Batch.Call<T, R> callable)");
    }

    @Override
    public <T extends Service, R> void coprocessorService(final Class<T> service, byte[] startKey, byte[] endKey,
                                                          final Batch.Call<T, R> callable,
                                                          final Batch.Callback<R> callback)
            throws ServiceException, Throwable {
        throw new UnsupportedOperationException("coprocessorService(final Class<T> service, byte[] startKey, "
                + "byte[] endKey, final Batch.Call<T, R> callable, final Batch.Callback<R> callback)");
    }

    @Deprecated
    @Override
    public long getWriteBufferSize() {
        return this.writeBufferSize;
    }

    @Deprecated
    @Override
    public void setWriteBufferSize(long writeBufferSize) throws IOException {
        this.writeBufferSize = writeBufferSize;
    }

    @Override
    public <R extends Message> Map<byte[], R> batchCoprocessorService(Descriptors.MethodDescriptor methodDescriptor,
                                                                      Message request, byte[] startKey, byte[] endKey,
                                                                      R responsePrototype)
            throws ServiceException, Throwable {
        throw new UnsupportedOperationException(
                "batchCoprocessorService(Descriptors.MethodDescriptor methodDescriptor, "
                        + "Message request, byte[] startKey, byte[] endKey, R responsePrototype)");
    }

    @Override
    public <R extends Message> void batchCoprocessorService(Descriptors.MethodDescriptor methodDescriptor,
            Message request, byte[] startKey, byte[] endKey, R responsePrototype,
            Batch.Callback<R> callback) throws ServiceException, Throwable {
        throw new UnsupportedOperationException(
                "batchCoprocessorService(Descriptors.MethodDescriptor methodDescriptor, "
                        + "Message request, byte[] startKey, byte[] endKey, R responsePrototype, "
                        + "Batch.Callback<R> callback)");
    }

    @Override
    public boolean checkAndMutate(byte[] row, byte[] family, byte[] qualifier, CompareFilter.CompareOp compareOp,
            byte[] value, RowMutations mutation) throws IOException {
        throw new UnsupportedOperationException("checkAndMutate(byte[] row, byte[] family, byte[] qualifier, "
                + "CompareFilter.CompareOp compareOp, byte[] value, RowMutations mutation)");
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
