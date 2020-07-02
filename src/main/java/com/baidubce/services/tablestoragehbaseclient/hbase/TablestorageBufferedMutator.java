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

package com.baidubce.services.tablestoragehbaseclient.hbase;

import com.baidubce.services.tablestoragehbaseclient.adaptor.Constants;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TableStorageAdaptor;
import com.baidubce.services.tablestoragehbaseclient.adaptor.TablestorageConfiguration;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TablestorageBufferedMutator derived from org.apache.hadoop.hbase.client.BufferedMutator
 * @date 2019/03/14 17:43:30
 */
public class TablestorageBufferedMutator implements BufferedMutator {
    private final TableName tableName;
    private TablestorageConnection connection;
    private TableStorageAdaptor adaptor;
    private volatile long writeBufferSize;
    private AtomicLong currentWriteBufferSize;
    private final ConcurrentLinkedQueue<Mutation> writeBuffer;
    private boolean closed;

    /**
     * Construct a TablestorageBufferedMutator for the target table.
     *
     * @param connection The connection used to construct this TablestorageBufferedMutator.
     * @param tableName The name of the target table.
     */
    public TablestorageBufferedMutator(TablestorageConnection connection, TableName tableName) {
        this.tableName = tableName;
        this.connection = connection;
        TablestorageConfiguration conf = connection.getTablestorageConfiguration();
        this.adaptor = new TableStorageAdaptor(conf.getEndpoint(), conf.getInstanceName(),
                conf.getAccessKeyId(), conf.getSecretAccessKey());
        writeBuffer = new ConcurrentLinkedQueue<Mutation>();
        this.currentWriteBufferSize = new AtomicLong(0);
        this.writeBufferSize = this.connection.getConfiguration().getLong("hbase.client.write.buffer",
                Constants.DEFAULT_WIRTE_BUFFER_SIZE);
        this.closed = false;
    }

    @Override
    public TableName getName() {
        return tableName;
    }

    @Override
    public Configuration getConfiguration() {
        return connection.getConfiguration();
    }

    @Override
    public void mutate(Mutation mutation) throws IOException {
        mutate(Collections.singletonList(mutation));
    }

    @Override
    public void mutate(List<? extends Mutation> mutations) throws IOException {
        if (closed) {
            throw new IOException("TableStorageBufferedMutator has been closed.");
        }
        for (Mutation mutation : mutations) {
            writeBuffer.add(mutation);
            currentWriteBufferSize.getAndAdd(mutation.heapSize());
        }
        if (currentWriteBufferSize.get() >= writeBufferSize) {
            flush();
        }
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        flush();
        adaptor.close();
    }

    @Override
    public void flush() throws IOException {
        List<Put> batchPuts = new ArrayList<Put>();
        List<Delete> batchDelets = new ArrayList<Delete>();

        Mutation mutation;
        while ((mutation = writeBuffer.poll()) != null) {
            if (mutation instanceof Put) {
                batchPuts.add((Put) mutation);
            } else if (mutation instanceof Delete) {
                batchDelets.add((Delete) mutation);
            }
        }
        if (batchPuts.size() != 0) {
            adaptor.batchPutRow(tableName.getNameAsString(), batchPuts);
        }
        if (batchDelets.size() != 0) {
            adaptor.batchDeleteRow(tableName.getNameAsString(), batchDelets);
        }
        currentWriteBufferSize.set(0);
    }

    @Override
    public long getWriteBufferSize() {
        return currentWriteBufferSize.get();
    }
}
