/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.baidubce.services.tablestoragehbaseclient.hbase;

import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestoragehbaseclient.adaptor.Constants;
import com.baidubce.services.tablestoragehbaseclient.util.HBaseTestUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tablestorage Client TestTable: Put/Get/Delete/Scan
 *
 * @date 2019/05/16 15:02:23
 */
public class TestTable {
    private static Logger logger = LoggerFactory.getLogger(TestTable.class);
    private static Connection connection;
    private static Admin admin;
    private Table table;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void classSetUp() throws IOException {
        logger.info("classSetUp start...");
        Configuration conf = HBaseConfiguration.create();
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
        } catch (IOException e) {
            logger.error("IOException", e);
            throw e;
        }
        logger.info("classSetUp finish!!!");
    }

    @AfterClass
    public static void classTearDown() throws IOException {
        logger.info("classTearDown start...");
        try {
            admin.close();
            connection.close();
        } catch (IOException e) {
            logger.error("IOException", e);
            throw e;
        }
        logger.info("classTearDown finish!!!");
    }

    @Before
    public void setUp() {
        logger.info("setUp start...");
        logger.info("setUp finish!!!");
    }

    @After
    public void tearDown() throws IOException {
        logger.info("tearDown start...");
        if (table != null) {
            table.close();
        }
        try {
            HBaseTestUtil.deleteTables(admin, false);
        } catch (IOException e) {
            logger.warn("IOException", e);
        }
        logger.info("tearDown finish!!!");
    }

    @Test
    public void testPutOfRowKeyLengthLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean succ = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(succ);
        table = connection.getTable(TableName.valueOf(tableName));

        String row0 = HBaseTestUtil.getStringWithChar(TableStorageConstants.MAX_ROWKEY_SIZE + 1, 'r');
        logger.info("start to put row with row len:" + row0.length());
        Put put0 = new Put(row0.getBytes());
        put0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        try {
            table.put(put0);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(),
                    containsString("The rowkey's size should not exceed the limit 4096. rowkeySize=4097."));
        }

        String row1 = HBaseTestUtil.getStringWithChar(TableStorageConstants.MAX_ROWKEY_SIZE, 'k');
        logger.info("start to put row with row len:" + row1.length());
        Put put1 = new Put(row1.getBytes());
        put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        table.put(put1);

        String row2 = "!@#$%^&*()?><:|{\"}";
        logger.info("start to put row with row:" + row2);
        Put put2 = new Put(row2.getBytes());
        put2.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        table.put(put2);
    }

    @Test
    public void testPutOfValueLengthLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        Put put0 = new Put("rowkey0".getBytes());
        String columnValue0 = HBaseTestUtil.getStringWithChar(TableStorageConstants.MAX_CELL_VALUE_SIZE, 'v');
        put0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), columnValue0.getBytes());
        table.put(put0);

        Put put1 = new Put("rowkey1".getBytes());
        String columnValue1 = HBaseTestUtil.getStringWithChar(TableStorageConstants.MAX_CELL_VALUE_SIZE + 1, 'v');
        put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), columnValue1.getBytes());
        try {
            table.put(put1);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The value's length should not exceed the limit 2097152."));
        }
    }

    @Test
    public void testPutOfColumnsNumberLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        Put put0 = new Put("rowkey".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_CELL_NUM; i++) {
            String columnName = "col" + i;
            put0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), "v".getBytes());
        }
        table.put(put0);

        Put put1 = new Put("rowkey1".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_CELL_NUM + 1; i++) {
            String columnName = "col" + i;
            put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), "v".getBytes());
        }
        try {
            table.put(put1);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The number of cells should not exceed the limit 1024."));
        }
    }

    @Test
    public void testPutOfRowLengthLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("step 1: start to put a row whose len gt 8M in one transactions");
        Put put0 = new Put("rowkey0".getBytes());
        int columnCount = 8;
        int columnValueLengthWithoutConsideringOthers = TableStorageConstants.MAX_ROW_SIZE / columnCount;
        String columnValue0 = HBaseTestUtil.getStringWithChar(columnValueLengthWithoutConsideringOthers, 'v');
        for (int i = 0; i < columnCount; i++) {
            String columnName = "col" + i;
            put0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue0.getBytes());
        }
        try {
            table.put(put0);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The row's size should not exceed the limit 8388608."));
        }

        logger.info("step 2: start to put a row whose len gt 8M in one transactions");
        Put put1 = new Put("rowkey1".getBytes());
        String columnValue1 = HBaseTestUtil.getStringWithChar(columnValueLengthWithoutConsideringOthers - 100, 'v');
        for (int i = 0; i < columnCount; i++) {
            String columnName = "col" + i;
            put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue1.getBytes());
        }
        table.put(put1);
    }

    @Test
    public void testBatchPutOfRowsNumberLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        List<Put> puts = new ArrayList<Put>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            puts.add(put);
        }
        table.put(puts);

        List<Put> puts1 = new ArrayList<Put>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM + 1; i++) {
            String rowkey = "rowkey_" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            puts1.add(put);
        }
        try {
            table.put(puts1);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The row's size should not exceed the limit 200"));
        }
    }

    @Test
    public void testBatchPutOfLengthLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        List<Put> puts = new ArrayList<Put>();
        Put put1 = new Put("rowkey1".getBytes());
        Put put2 = new Put("rowkey2".getBytes());
        int rowCount = puts.size();
        int cellCountOfRow = 5;
        int maxCellValueLengthWithConsideringOthers =
                TableStorageConstants.MAX_REQUEST_BODY_SIZE / rowCount / cellCountOfRow;
        String columnValue = HBaseTestUtil.getStringWithChar(maxCellValueLengthWithConsideringOthers, 'v');
        for (int i = 0; i < rowCount; i++) {
            String columnName = "col" + i;
            put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue.getBytes());
            put2.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue.getBytes());
        }
        puts.add(put1);
        puts.add(put2);
        try {
            table.put(puts);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("Request body's size should not exceed the limit 10485760."));
        }

        List<Put> puts2 = new ArrayList<Put>();
        Put put3 = new Put("rowkey1".getBytes());
        Put put4 = new Put("rowkey2".getBytes());
        columnValue = HBaseTestUtil.getStringWithChar(maxCellValueLengthWithConsideringOthers - 100, 'v');
        for (int i = 0; i < rowCount; i++) {
            String columnName = "col" + i;

            put3.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue.getBytes());
            put4.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), columnValue.getBytes());
        }
        puts2.add(put3);
        puts2.add(put4);
        table.put(puts2);
    }

    @Test
    public void testGetRow() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put("rowkey0".getBytes());
        put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        table.put(put);

        Get get0 = new Get("rowkey0".getBytes());
        get0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes());
        Result result0 = table.get(get0);
        assertEquals(1, result0.size());
        assertEquals("v", Bytes.toString(result0.getValue(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes())));

        Get get1 = new Get("rowkey0".getBytes());
        Result result1 = table.get(get1);
        assertEquals(1, result1.size());
        assertEquals("v", Bytes.toString(result1.getValue(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes())));

        Get get2 = new Get("rowkey_not_exist".getBytes());
        Result result2 = table.get(get2);
        assertEquals(0, result2.size());

        assertTrue(table.exists(get0));
        assertFalse(table.exists(get2));

        StringBuilder rowkey3 = new StringBuilder("rowkey_not_exist");
        for (int i = rowkey3.length(); i < TableStorageConstants.MAX_ROWKEY_SIZE; i++) {
            rowkey3.append("k");
        }
        Get get3 = new Get(rowkey3.toString().getBytes());
        Result result3 = table.get(get3);
        assertEquals(0, result3.size());
        assertFalse(table.exists(get3));
    }

    @Test
    public void testGetOfColumnsNumberLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put("rowkey".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_CELL_NUM; i++) {
            String columnName = "col" + i;
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), "v".getBytes());
        }
        table.put(put);

        Get get0 = new Get("rowkey1".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_READ_CELL_NUM; i++) {
            String columnName = "col" + i;
            get0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes());
        }
        table.get(get0);

        Get get1 = new Get("rowkey1".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_READ_CELL_NUM + 1; i++) {
            String columnName = "col" + i;
            get1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes());
        }
        try {
            table.get(get1);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("Payload cannot exceed upper limit."));
        }
    }

    @Test
    public void testBatchGet() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to batch put 10 rows");
        for (int i = 0; i < 10; i++) {
            String rowkey = "rowkey" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            table.put(put);
        }

        logger.info("Step 1: start to batch get 5 rows");
        List<Get> gets = new ArrayList<Get>();
        for (int i = 0; i < 10; i += 2) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            gets.add(get);
        }
        Result[] results = table.get(gets);
        assertEquals(5, results.length);

        logger.info("Step 1: start to batch get 11 rows with one row not existing");
        List<Get> gets1 = new ArrayList<Get>();
        for (int i = 0; i < 11; i++) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            gets1.add(get);
        }
        Result[] results1 = table.get(gets1);
        // assertEquals(10, results1.length);
        assertEquals(11, results1.length);
        for (int i = 0; i < 10; i++) {
            assertFalse(results1[i].isEmpty());
        }
        assertTrue(results1[10].isEmpty());
    }

    @Test
    public void testBatchGetRowKeyLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put 200 rows");
        List<Put> puts = new ArrayList<Put>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            puts.add(put);
        }
        table.put(puts);

        logger.info("Step 2: start to get 101 rows, fail");
        List<Get> gets = new ArrayList<Get>();
        for (int i = 0; i < TableStorageConstants.MAX_READ_ROW_NUM + 1; i++) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            gets.add(get);
        }
        try {
            table.get(gets);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The row's size should not exceed the limit 100"));
        }

        logger.info("Step 3: start to get 100 rows, succ");
        List<Get> gets1 = new ArrayList<Get>();
        for (int i = 0; i < TableStorageConstants.MAX_READ_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            gets1.add(get);
        }
        table.get(gets1);
    }

    @Test
    public void testDelete() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put("rowkey".getBytes());
        put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        table.put(put);

        Delete delete = new Delete("rowkey".getBytes());
        table.delete(delete);

        Get get = new Get("rowkey".getBytes());
        Result result = table.get(get);
        assertEquals(0, result.size());
    }

    @Test
    public void testDeleteWithOneColumn() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put a row with 10 columns");
        Put put = new Put("rowkey".getBytes());
        for (int i = 0; i < 10; i++) {
            String columnName = "col" + i;
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), "v".getBytes());
        }
        table.put(put);

        logger.info("Step 2: start to delete a column");
        Delete delete = new Delete("rowkey".getBytes());
        delete.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes());
        table.delete(delete);

        logger.info("Step 3: start to get the row, 9 columns exist");
        Get get = new Get("rowkey".getBytes());
        Result result = table.get(get);
        assertEquals(9, result.size());
        assertFalse(result.containsColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes()));

        logger.info("Step 3.1: start to get the column which is deleted");
        get.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes());
        Result result1 = table.get(get);
        assertEquals(0, result1.size());
    }

    @Test
    public void testDeleteWithMultiColumns() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put a row with 3072 columns");
        for (int i = 0; i < 3; i++) {
            Put put = new Put("rowkey".getBytes());
            for (int j = 0; j < TableStorageConstants.MAX_WRITE_CELL_NUM; j++) {
                String columnName = "col" + (TableStorageConstants.MAX_WRITE_CELL_NUM * i + j);
                put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes(), "v".getBytes());
            }
            table.put(put);
        }

        logger.info("Step 2: start to delete a row with 1025 columns, fail");
        Delete delete0 = new Delete("rowkey".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_CELL_NUM + 1; i++) {
            String columnName = "col" + i;
            delete0.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes());
        }
        try {
            table.delete(delete0);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The number of cells should not exceed the limit 1024."));
        }

        logger.info("Step 2.1: start to check the row by get");
        Get get0 = new Get("rowkey".getBytes());
        Result result0 = table.get(get0);
        assertFalse(result0.isEmpty());
        assertEquals(TableStorageConstants.MAX_WRITE_CELL_NUM * 3, result0.size());

        logger.info("Step 3: start to delete a row with 1024 columns, succ");
        Delete delete1 = new Delete("rowkey".getBytes());
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_CELL_NUM; i++) {
            String columnName = "col" + i;
            delete1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), columnName.getBytes());
        }
        table.delete(delete1);

        logger.info("Step 3.1: start to check the row by get");
        Get get1 = new Get("rowkey".getBytes());
        Result result1 = table.get(get1);
        assertFalse(result1.isEmpty());
        assertEquals(TableStorageConstants.MAX_WRITE_CELL_NUM * 2, result1.size());

        logger.info("Step 4: start to delete a row with 1024 columns, succ");
        Delete delete2 = new Delete("rowkey".getBytes());
        table.delete(delete2);

        logger.info("Step 4.1: start to check the row by get");
        Get get2 = new Get("rowkey".getBytes());
        Result result2 = table.get(get2);
        assertTrue(result2.isEmpty());
        assertEquals(0, result2.size());
    }

    @Test
    public void testBatchDeleteOfRowKeyLimit() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put 200 rows");
        List<Put> puts = new ArrayList<Put>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            puts.add(put);
        }
        table.put(puts);

        logger.info("Step 2: start to delete 201 rows, fail");
        List<Delete> deletes = new ArrayList<Delete>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM + 1; i++) {
            String rowkey = "rowkey" + i;
            Delete delete = new Delete(rowkey.getBytes());
            deletes.add(delete);
        }
        try {
            table.delete(deletes);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString(
                    "The row's size should not exceed the limit 200 in Put and Delete operations. rowSize="));
        }
        logger.info("Step 2.1: start to check rows are still existing");
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            Result result = table.get(get);
            assertFalse(result.isEmpty());
        }

        logger.info("Step 3: start to delete 200 rows, succ");
        List<Delete> deletes1 = new ArrayList<Delete>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Delete delete = new Delete(rowkey.getBytes());
            deletes1.add(delete);
        }
        table.delete(deletes1);
        logger.info("Step 3.1: start to check rows are not existing");
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Get get = new Get(rowkey.getBytes());
            Result result = table.get(get);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    public void testScanWithStartRowLitterThanStopRow() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put 200 rows");
        List<Put> puts = new ArrayList<Put>();
        for (int i = 0; i < TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            String rowkey = "rowkey" + i;
            Put put = new Put(rowkey.getBytes());
            put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
            puts.add(put);
        }
        table.put(puts);

        logger.info("Step 2: start to scan, start row is litter than end row");
        Scan scan = new Scan();
        scan.setStartRow("rowkey18".getBytes());
        scan.setStopRow("rowkey10".getBytes());
        ResultScanner scanner = table.getScanner(scan);
        try {
            scanner.next();
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("The stopRowkey's value "));
            assertThat(e.getMessage(), containsString("must be greater than the startRowkey's value "));
        }
    }

    @Test
    public void testScan() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        table = connection.getTable(TableName.valueOf(tableName));

        logger.info("Step 1: start to put 20200 rows");
        int batchWriteCount = 101;
        int index = 0;
        for (int i = 0; i < batchWriteCount; i++) {
            List<Put> puts = new ArrayList<Put>();
            for (int j = 0; j < TableStorageConstants.MAX_WRITE_ROW_NUM; j++, index++) {
                String rowkey = String.format("rowkey_%05d", index);
                Put put = new Put(rowkey.getBytes());
                put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
                puts.add(put);
            }
            table.put(puts);
        }

        logger.info("Step 2: start to delete 12000 rows");
        index = 0;
        for (int i = 0; i < batchWriteCount; i++) {
            List<Delete> deletes = new ArrayList<Delete>();
            for (int j = 0; j < TableStorageConstants.MAX_WRITE_ROW_NUM; j++, index++) {
                if (index % 2 == 0) {
                    continue;
                }
                String rowkey = String.format("rowkey_%05d", index);
                Delete delete = new Delete(rowkey.getBytes());
                delete.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes());
                deletes.add(delete);
            }
            table.delete(deletes);
        }

        logger.info("Step 3: start to scan");
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        index = 0;
        for (Result result : scanner) {
            assertEquals(String.format("rowkey_%05d", index), Bytes.toString(result.getRow()));
            index += 2;
        }
        assertEquals(TableStorageConstants.MAX_WRITE_ROW_NUM * batchWriteCount, index);
    }

    @Test
    public void testMulReadWrite() throws IOException {
        String tableName = HBaseTestUtil.getTableName();

        HBaseTestUtil.createTable(admin, tableName);

        table = connection.getTable(TableName.valueOf(tableName));

        // list table check table name
        TableName[] tableName1 = admin.listTableNames(tableName);
        assertEquals(1, tableName1.length);
        assertEquals(tableName, tableName1[0].getNameAsString());

        // get table schema
        HTableDescriptor descriptor = admin.getTableDescriptor(TableName.valueOf(tableName));
        assertEquals(tableName, descriptor.getTableName().getNameAsString());
        assertEquals(1, descriptor.getColumnFamilies().length);
        assertEquals(Constants.DEFAULT_FAMILY, descriptor.getColumnFamilies()[0].getNameAsString());

        // put data
        Put put = new Put(("rowkey1").getBytes());
        put.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes(), "v".getBytes());
        table.put(put);

        // get data
        Get get = new Get("rowkey1".getBytes());
        get.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes());
        Result result = table.get(get);
        assertEquals(1, result.size());
        assertEquals("v", Bytes.toString(result.getValue(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes())));

        // batch put data
        List<Put> putList = new ArrayList<Put>();
        List<Get> getList = new ArrayList<Get>();
        List<Delete> deleteList = new ArrayList<Delete>();
        for (int i = 0; i < 10; i++) {
            Put put1 = new Put(("rowkey" + i).getBytes());
            Get get1 = new Get(("rowkey" + i).getBytes());
            Delete delete1 = new Delete(("rowkey" + i).getBytes());
            for (int j = 0; j < 10; j++) {
                put1.addColumn(Constants.DEFAULT_FAMILY.getBytes(), ("col" + j).getBytes(), "v".getBytes());
            }
            putList.add(put1);
            getList.add(get1);
            deleteList.add(delete1);
        }
        table.put(putList);

        // batch get data and check
        Result[] results = table.get(getList);
        assertEquals(10, results.length);
        for (int i = 0; i < 10; i++) {
            assertEquals("v",
                    Bytes.toString(results[i].getValue(Constants.DEFAULT_FAMILY.getBytes(), ("col" + i).getBytes())));
        }

        // scan data
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        Iterator it = scanner.iterator();
        Result cell;
        int count = 0;
        while (it.hasNext()) {
            cell = (Result) it.next();
            ++count;
            assertEquals(10, cell.size());
            assertEquals("v", Bytes.toString(cell.getValue(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes())));
        }
        assertEquals(10, count);
        scanner.close();

        // del data
        Delete delete = new Delete("rowkey1".getBytes());
        table.delete(delete);

        // batch del data
        table.delete(deleteList);

        // buffered mutation mutate only for put&del
        logger.info("mutate start...");
        BufferedMutator bufferedMutator = connection.getBufferedMutator(TableName.valueOf(tableName));
        bufferedMutator.mutate(put);
        Put put2 = new Put("rowkey1".getBytes());
        put2.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col1".getBytes(), "v".getBytes());
        bufferedMutator.mutate(put2);
        assertEquals(912, bufferedMutator.getWriteBufferSize());

        Delete delete2 = new Delete("rowkey1".getBytes());
        delete2.addColumn(Constants.DEFAULT_FAMILY.getBytes(), "col1".getBytes());
        bufferedMutator.mutate(delete2);
        assertEquals(1368, bufferedMutator.getWriteBufferSize());
        bufferedMutator.flush();

        // now, data of get only contains col0
        Get get1 = new Get("rowkey1".getBytes());
        result = table.get(get1);
        logger.info("get data is " + result.toString());
        assertEquals(1, result.size());
        assertTrue(result.containsColumn(Constants.DEFAULT_FAMILY.getBytes(), "col0".getBytes()));
        assertFalse(result.containsColumn(Constants.DEFAULT_FAMILY.getBytes(), "col1".getBytes()));

        // batchMutate
        List<Mutation> mutationList = new ArrayList<Mutation>();
        for (int i = 0; i < 10; i++) {
            Put putMutation = new Put(("rowkey" + i).getBytes());
            for (int j = 0; j < 10; j++) {
                putMutation.addColumn(Constants.DEFAULT_FAMILY.getBytes(), ("col" + j).getBytes(), "v".getBytes());
            }
            mutationList.add(putMutation);
        }

        // delete 5 rows
        for (int i = 0; i < 5; i++) {
            Delete deleteMutation = new Delete(("rowkey" + i).getBytes());
            mutationList.add(deleteMutation);
        }

        bufferedMutator.mutate(mutationList);
        assertEquals(15480, bufferedMutator.getWriteBufferSize());
        bufferedMutator.flush();

        // after delete, batch get 10 rows，only 5 rows returned，Mutator doesn't guarantee the order of services.
        Result[] results1 = table.get(getList);
        assertEquals(10, results1.length);

        for (int i = 0; i < 5; i++) {
            if (results1[0].isEmpty()) {    // if the first data is empty, so put is do first, the 1-5 row is deleted
                assertTrue(results1[i].isEmpty());
            } else {
                // del do first，the 0-5 row is existing
                assertEquals("rowkey" + i, Bytes.toString(results1[i].getRow()));
            }
        }
        bufferedMutator.close();
        table.close();
    }
}
