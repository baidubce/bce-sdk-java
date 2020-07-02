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

package com.baidubce.services.tablestoragehbaseclient.adaptor;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tablestorage.TableStorageClient;
import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.BatchDeleteRowRequest;
import com.baidubce.services.tablestorage.model.BatchGetRowRequest;
import com.baidubce.services.tablestorage.model.BatchGetRowResponse;
import com.baidubce.services.tablestorage.model.BatchPutRowRequest;
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.CreateTableRequest;
import com.baidubce.services.tablestorage.model.DeleteRow;
import com.baidubce.services.tablestorage.model.DeleteRowRequest;
import com.baidubce.services.tablestorage.model.DropTableRequest;
import com.baidubce.services.tablestorage.model.GetRow;
import com.baidubce.services.tablestorage.model.GetRowRequest;
import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.services.tablestorage.model.ListKeyRangesRequest;
import com.baidubce.services.tablestorage.model.ListKeyRangesResponse;
import com.baidubce.services.tablestorage.model.ListTablesRequest;
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.ListTablesResponse.TableInfo;
import com.baidubce.services.tablestorage.model.PutRow;
import com.baidubce.services.tablestorage.model.PutRowRequest;
import com.baidubce.services.tablestorage.model.ScanRequest;
import com.baidubce.services.tablestorage.model.ScanResponse;
import com.baidubce.services.tablestorage.model.ShowTableRequest;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.TableOption;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import com.baidubce.services.tablestorage.model.UpdateTableRequest;
import com.baidubce.util.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

/**
 * Used to access Tablestorage service.
 */
public class TableStorageAdaptor {
    private TableStorageClient internalClient;

    /**
     * Construct a TableStorage Adaptor to connect with TableStorage.
     *
     * @param endpoint TableStorage endpoint e.g. bts.bd.baidubce.com.
     * @param instanceName The target instance name.
     * @param accessKeyId User's accessKeyId.
     * @param secretAccessKey User's secretAccessKey.
     */
    public TableStorageAdaptor(String endpoint, String instanceName, String accessKeyId, String secretAccessKey) {
        BceCredentials credentials = new DefaultBceCredentials(accessKeyId, secretAccessKey);
        BceClientConfiguration configuration = new BceClientConfiguration();
        configuration.setCredentials(credentials);
        configuration.setEndpoint(endpoint);

        internalClient = new TableStorageClient(configuration, instanceName);
    }

    /**
     * Get the Table Descriptor from TableStorage.
     *
     * @param tableName The name of the table.
     * @return The Table Descriptor
     * @throws IOException If a permission error or remote error occurs
     */
    public HTableDescriptor getTable(String tableName) throws IOException {
        ShowTableResponse response = null;
        try {
            response = internalClient.showTable(new ShowTableRequest(tableName));
        } catch (BceClientException e) {
            if (e instanceof BceServiceException
                    && ((BceServiceException) e).getErrorCode().equals(Constants.TABLE_NOT_EXIST_CODE)) {
                return null;
            }
            throw new IOException(e.getMessage(), e);
        }

        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));

        HColumnDescriptor familyDescriptor = new HColumnDescriptor(Constants.DEFAULT_FAMILY);
        familyDescriptor.setCompressionType(TablestorageConvertor.toCompressionAlgorithm(response.getCompressType()));
        familyDescriptor.setTimeToLive(response.getTimeToLive());
        descriptor.addFamily(familyDescriptor);

        return descriptor;
    }

    /**
     * List tables from TableStorage.
     *
     * @return A List of table name.
     * @throws IOException If a permission error or remote error occurs
     */
    public List<String> listTable() throws IOException {
        ListTablesRequest listTablesRequest = new ListTablesRequest();
        ListTablesResponse response = null;
        try {
            response = internalClient.listTables(listTablesRequest);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }

        List<String> tableNames = new ArrayList<String>(response.getTables().size());
        for (TableInfo info : response.getTables()) {
            tableNames.add(info.getTableName());
        }
        return tableNames;
    }

    /**
     * Create table in TableStorage.
     *
     * @param tableName The name of the table.
     * @param compressType The compression type of the table.
     * @param maxVersions The max versions of the table.
     * @param ttl TimeToLive of the table, in seconds.
     * @throws IOException If a permission error or remote error occurs
     */
    public void createTable(String tableName, CompressType compressType, int maxVersions, int ttl) throws IOException {
        TableOption option = new TableOption();
        option.setCompressType(compressType);
        option.setTableVersion(TableStorageConstants.CREATE_TABLE_VERSION);
        option.setMaxVersions(maxVersions);
        option.setTimeToLive(ttl);

        CreateTableRequest request = new CreateTableRequest(tableName, option);
        try {
            internalClient.createTable(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Drop table in TableStorage
     *
     * @param tableName The name of the table.
     * @throws IOException If a permission error or remote error occurs
     */
    public void dropTable(String tableName) throws IOException {
        DropTableRequest request = new DropTableRequest(tableName);
        try {
            internalClient.dropTable(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
    /**
     * Check if the table is available
     *
     * @param tableName The name of the table.
     * @return true if the table is available, or false for not.
     * @throws IOException If a permission error or remote error occurs.
     */
    public boolean isTableAvailable(String tableName) throws IOException {
        ShowTableResponse response = null;
        try {
            response = internalClient.showTable(new ShowTableRequest(tableName));
        } catch (BceClientException e) {
            if (e instanceof BceServiceException
                    && ((BceServiceException) e).getErrorCode().equals(Constants.TABLE_NOT_EXIST_CODE)) {
                return false;
            }
            throw new IOException(e.getMessage(), e);
        }

        long createTime = DateUtils.parseIso8601Date(response.getCreateTime()).getTime();
        return System.currentTimeMillis() - createTime > Constants.CREATE_TABLE_WAIT_TIME
                && response.getTableState() == TableState.Normal;
    }

    /**
     * Update table in TableStorage.
     *
     * @param tableName The name of the table.
     * @param compressType The compression type of the table.
     * @param maxVersions The max versions of the table.
     * @param ttl TimeToLive of the table, in seconds.
     * @throws IOException If a permission error or remote error occurs.
     */
    public void updateTable(String tableName, CompressType compressType, int maxVersions, int ttl) throws IOException {
        ShowTableResponse response = null;
        try {
            response = internalClient.showTable(new ShowTableRequest(tableName));
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }

        TableOption option = new TableOption();
        option.setTableVersion(response.getTableVersion());
        option.setMaxVersions(maxVersions);
        option.setCompressType(compressType);
        option.setTimeToLive(ttl);

        UpdateTableRequest request = new UpdateTableRequest(tableName, option);
        try {
            internalClient.updateTable(request);
        } catch (BceClientException e) {
            if (e instanceof BceServiceException
                    && ((BceServiceException) e).getErrorCode().equals(Constants.INVALID_TABLE_VERSION_CODE)) {
                throw new IOException("Update failed, please try again! RequestId="
                        + ((BceServiceException) e).getRequestId(), e);
            }
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Perform Row operations in batch.
     * The order of executions is the same as the order of actions.
     *
     * @param tableName The name of the table.
     * @param actions List of Row operation. Only support Get, Put and Delete.
     * @return Result of actions.
     * @throws IOException If a permission error or remote error occurs.
     */
    public Object[] batch(String tableName, final List<? extends Row> actions) throws IOException {
        List<Object> resultList = new ArrayList<Object>();
        List<Put> putList = new ArrayList<Put>();
        List<Delete> deleteList = new ArrayList<Delete>();
        List<Get> getList = new ArrayList<Get>();

        int type = Constants.NON_TYPE;
        int lastType = Constants.NON_TYPE;
        int num = 0;
        for (int i = 0; i <= actions.size(); i++) {
            num++;
            if (i < actions.size()) {
                Row row = actions.get(i);
                if (row instanceof Put) {
                    type = Constants.PUT_TYPE;
                    putList.add((Put) row);
                } else if (row instanceof Delete) {
                    type = Constants.DELETE_TYPE;
                    deleteList.add((Delete) row);
                } else if (row instanceof Get) {
                    type = Constants.GET_TYPE;
                    getList.add((Get) row);
                }
            } else {
                type = Constants.NON_TYPE;
            }

            if (lastType != Constants.NON_TYPE && type != lastType) {
                switch (lastType) {
                    case Constants.PUT_TYPE: {
                        batchPutRow(tableName, putList);
                        Object[] results = new Object[num - 1];
                        resultList.addAll(Arrays.asList(results));
                        putList.clear();
                        num = 1;
                        break;
                    }
                    case Constants.DELETE_TYPE: {
                        batchDeleteRow(tableName, deleteList);
                        Object[] results = new Object[num - 1];
                        resultList.addAll(Arrays.asList(results));
                        deleteList.clear();
                        num = 1;
                        break;
                    }
                    case Constants.GET_TYPE: {
                        Object[] results = batchGetRow(tableName, getList);
                        resultList.addAll(Arrays.asList(results));
                        getList.clear();
                        num = 1;
                        break;
                    }
                    default:
                        throw new UnsupportedOperationException("batch operation only support Put, Delete and Get");
                }
            }

            lastType = type;
        }

        Object[] result = new Object[actions.size()];
        return resultList.toArray(result);
    }

    /**
     * Get a row in the table.
     *
     * @param tableName The name of the target table.
     * @param get Used to specify the target data in the table.
     * @return Data in the table.
     * @throws IOException If a permission error or remote error occurs.
     */
    public Result getRow(String tableName, Get get) throws IOException {
        GetRowRequest request = new GetRowRequest(tableName, Bytes.toString(get.getRow()));

        if (get.getFamilyMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }
        for (Map.Entry<byte[], NavigableSet<byte[]>> familyEntry : get.getFamilyMap().entrySet()) {
            if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(familyEntry.getKey()))) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            if (familyEntry.getValue() != null && !familyEntry.getValue().isEmpty()) {
                for (byte[] qualifier : familyEntry.getValue()) {
                    request.addCell(Bytes.toString(qualifier));
                }
            }
        }
        request.setMaxVersions(get.getMaxVersions());

        try {
            GetRowResponse response = internalClient.getRow(request);
            TableStorageResult result = response.getResult();
            return TablestorageConvertor.toHBaseResult(result);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Batch get rows in the table.
     *
     * @param tableName The name of the target table.
     * @param gets List of Gets.
     * @return Data in the table.
     * @throws IOException If a permission error or remote error occurs.
     */
    public Result[] batchGetRow(String tableName, List<Get> gets) throws IOException {
        BatchGetRowRequest request = new BatchGetRowRequest(tableName);

        for (Get get : gets) {
            GetRow getRow = new GetRow(Bytes.toString(get.getRow()));
            if (get.getFamilyMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            for (Map.Entry<byte[], NavigableSet<byte[]>> familyEntry : get.getFamilyMap().entrySet()) {
                if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(familyEntry.getKey()))) {
                    throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                }
                if (familyEntry.getValue() != null && !familyEntry.getValue().isEmpty()) {
                    for (byte[] qualifier : familyEntry.getValue()) {
                        getRow.addCell(Bytes.toString(qualifier));
                    }
                }
            }
            getRow.setMaxVersions(get.getMaxVersions());
            request.addGetRow(getRow);
        }

        try {
            BatchGetRowResponse batchGetRowResponse =  internalClient.batchGetRow(request);
            List<TableStorageResult> tableStorageResults = batchGetRowResponse.getResults();
            return TablestorageConvertor.toBatchGetHBaseResults(gets, tableStorageResults);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Put a row to the table.
     *
     * @param tableName The name of the target table.
     * @param put Used to describe a single row.
     * @throws IOException If a permission error or remote error occurs.
     */
    public void putRow(String tableName, Put put) throws IOException {
        PutRowRequest request = new PutRowRequest(tableName, Bytes.toString(put.getRow()));

        if (put.getFamilyCellMap().size() != Constants.MAX_COLUMN_FAMILY_NUM) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }
        for (Map.Entry<byte[], List<Cell>> entry : put.getFamilyCellMap().entrySet()) {
            if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(entry.getKey()))) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            for (Cell cell : entry.getValue()) {
                String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                request.addCell(column, value);
            }
        }

        try {
            internalClient.putRow(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Batch put rows to the table.
     *
     * @param tableName The name of the target table.
     * @param puts List of Puts.
     * @throws IOException If a permission error or remote error occurs.
     */
    public void batchPutRow(String tableName, List<Put> puts) throws IOException {
        BatchPutRowRequest request = new BatchPutRowRequest(tableName);

        for (Put put : puts) {
            PutRow putRow = new PutRow(Bytes.toString(put.getRow()));
            if (put.getFamilyCellMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            for (Map.Entry<byte[], List<Cell>> entry : put.getFamilyCellMap().entrySet()) {
                if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(entry.getKey()))) {
                    throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                }
                for (Cell cell : entry.getValue()) {
                    String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    putRow.addCell(column, value);
                }
            }
            request.addPutRow(putRow);
        }

        try {
            internalClient.batchPutRow(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Delete a row in the table.
     *
     * @param tableName The name of the target table.
     * @param delete Used to specify the target data in the table.
     * @throws IOException If a permission error or remote error occurs.
     */
    public void deleteRow(String tableName, Delete delete) throws IOException {
        DeleteRowRequest request = new DeleteRowRequest(tableName, Bytes.toString(delete.getRow()));

        if (delete.getFamilyCellMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }
        for (Map.Entry<byte[], List<Cell>> entry : delete.getFamilyCellMap().entrySet()) {
            if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(entry.getKey()))) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            for (Cell cell : entry.getValue()) {
                String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                request.addCell(column);
            }
        }

        try {
            internalClient.deleteRow(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Batch delete rows in the table
     *
     * @param tableName The name of the target table.
     * @param deletes List of Deletes.
     * @throws IOException If a permission error or remote error occurs.
     */
    public void batchDeleteRow(String tableName, List<Delete> deletes) throws IOException {
        BatchDeleteRowRequest request = new BatchDeleteRowRequest(tableName);

        for (Delete delete : deletes) {
            DeleteRow deleteRow = new DeleteRow(Bytes.toString(delete.getRow()));
            if (delete.getFamilyCellMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            for (Map.Entry<byte[], List<Cell>> entry : delete.getFamilyCellMap().entrySet()) {
                if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(entry.getKey()))) {
                    throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
                }
                for (Cell cell : entry.getValue()) {
                    String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                    deleteRow.addCell(column);
                }
            }
            request.addDeleteRow(deleteRow);
        }

        try {
            internalClient.batchDeleteRow(request);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Scan data in the table.
     *
     * @param tableName The name of the target table.
     * @param scan Used to specify the target data in the table.
     * @param startRowkey This scan will start with the maximum of
     *                    scan.getStartRow() and startRowkey.
     * @param nextStartRowkeyStream Next scan should start with it.
     * @return Data in the table.
     * @throws IOException If a permission error or remote error occurs.
     */
    public List<Result> scan(String tableName, Scan scan, String startRowkey,
                             ByteArrayOutputStream nextStartRowkeyStream) throws IOException {
        List<Result> results = new ArrayList<Result>();
        ScanRequest request = new ScanRequest(tableName);

        if (scan.getFamilyMap().size() > Constants.MAX_COLUMN_FAMILY_NUM) {
            throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
        }
        for (Map.Entry<byte[], NavigableSet<byte[]>> entry : scan.getFamilyMap().entrySet()) {
            if (!Constants.DEFAULT_FAMILY.equals(Bytes.toString(entry.getKey()))) {
                throw new UnsupportedOperationException(Constants.WRONG_COLUMN_FAMILY_SIZE_MSG);
            }
            if (null == entry.getValue()) {
                continue;
            }
            for (byte[] columnBytes : entry.getValue()) {
                String columnStr = Bytes.toString(columnBytes);
                request.addSelector(columnStr);
            }
        }

        String scanStartRowkey = Bytes.toString(scan.getStartRow());
        if (startRowkey.compareTo(scanStartRowkey) > 0) {
            request.setStartRowkey(startRowkey, true);
        } else {
            request.setStartRowkey(scanStartRowkey, true);
        }
        if (Bytes.compareTo(scan.getStopRow(), HConstants.EMPTY_END_ROW) != 0) {
            request.setStopRowkey(Bytes.toString(scan.getStopRow()), false);
        }
        request.setMaxVersions(scan.getMaxVersions());

        try {
            ScanResponse response = internalClient.scan(request);

            nextStartRowkeyStream.write(response.getNextStartKey().getBytes());
            for (TableStorageResult tablestorageResult : response.getResults()) {
                results.add(TablestorageConvertor.toHBaseResult(tablestorageResult));
            }
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
        return results;
    }

    /**
     * Get the minimum key for every region in the table.
     *
     * @param tableName The name of the target table.
     * @return Array of minimum key.
     * @throws IOException If a permission error or remote error occurs.
     */
    public byte[][] getStartKeys(String tableName) throws IOException {
        ListKeyRangesRequest request = new ListKeyRangesRequest(tableName);
        try {
            ListKeyRangesResponse response = internalClient.listKeyRanges(request);
            List<Pair<String, String>> keyRanges = response.getKeyRanges();

            byte[][] startKeys = new byte[keyRanges.size()][];
            int index = 0;
            for (Pair<String, String> keyRange : keyRanges) {
                startKeys[index++] = keyRange.getLeft().getBytes();
            }
            return startKeys;
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Get the maximum key for every region in the table.
     *
     * @param tableName The name of the target table.
     * @return Array of maximum key.
     * @throws IOException If a permission error or remote error occurs.
     */
    public byte[][] getEndKeys(String tableName) throws IOException {
        ListKeyRangesRequest request = new ListKeyRangesRequest(tableName);
        try {
            ListKeyRangesResponse response = internalClient.listKeyRanges(request);
            List<Pair<String, String>> keyRanges = response.getKeyRanges();

            byte[][] endKeys = new byte[keyRanges.size()][];
            int index = 0;
            for (Pair<String, String> keyRange : keyRanges) {
                endKeys[index++] = keyRange.getRight().getBytes();
            }
            return endKeys;
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Get the minimum key and the maximum key for every region in the table.
     *
     * @param tableName The name of the target table.
     * @return Pair of minimum key and maximum key.
     * @throws IOException If a permission error or remote error occurs.
     */
    public org.apache.hadoop.hbase.util.Pair<byte[][], byte[][]> getStartEndKeys(String tableName) throws IOException {
        ListKeyRangesRequest request = new ListKeyRangesRequest(tableName);
        try {
            ListKeyRangesResponse response = internalClient.listKeyRanges(request);
            List<Pair<String, String>> keyRanges = response.getKeyRanges();

            byte[][] startKeys = new byte[keyRanges.size()][];
            byte[][] endKeys =  new byte[keyRanges.size()][];
            int index = 0;
            for (Pair<String, String> keyRange : keyRanges) {
                startKeys[index] = keyRange.getLeft().getBytes();
                endKeys[index] = keyRange.getRight().getBytes();
                index++;
            }
            return new org.apache.hadoop.hbase.util.Pair<byte[][], byte[][]>(startKeys, endKeys);
        } catch (BceClientException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Close the connection with TableStorage.
     */
    public void close() {
        internalClient.shutdown();
    }

}
