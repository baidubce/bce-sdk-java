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
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.io.compress.Compression;
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
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tablestorage Client TestAdmin
 *
 * @date 2019/05/16 15:02:23
 */
public class TestAdmin {
    private static Logger logger = LoggerFactory.getLogger(TestAdmin.class);
    private static Connection connection;
    private static Admin admin;
    private static final int maxTableNameLength = 255;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void classSetUp() throws IOException {
        logger.info("classSetUp start...");
        Configuration conf = HBaseConfiguration.create();
        try {
            Connection connection = ConnectionFactory.createConnection(conf);
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
    public void tearDown() {
        logger.info("tearDown start...");
        try {
            HBaseTestUtil.deleteTables(admin, false);
        } catch (IOException e) {
            logger.warn("IOException", e);
        }
        logger.info("tearDown finish!!!");
    }

    @Test
    public void testTableNameParamCheckOfEmpty() {
        logger.info("start to test TableName class para check");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Table qualifier must not be empty");
        TableName.valueOf("");
    }

    @Test
    public void testTableNameParamCheckOfIllegalChar() {
        logger.info("start to test TableName class para check");
        String specialChars = "!@#$%^&*()?><:|{\"}";
        for (int i = 0; i < specialChars.length(); i++) {
            String tableName = HBaseTestUtil.getTableName() + specialChars.charAt(i);
            logger.info(tableName);
            try {
                TableName.valueOf(specialChars);
                fail();
            } catch (IllegalArgumentException e) {
                logger.info("IllegalArgumentException", e);
                assertThat(e.getMessage(), containsString("Illegal character"));
            }
        }
    }

    @Test
    public void testCreateTable() throws IOException {
        logger.info("start to test createTable function");
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);
        byte[][] splitKeys = Bytes.toByteArrays("www.baidu.com/1");
        assertTrue(admin.isTableAvailable(TableName.valueOf(tableName), splitKeys));
    }

    @Test
    public void testCreateTableWithTableNameExceedsMaxLength() throws IOException {
        StringBuilder tooLongTableName = new StringBuilder(HBaseTestUtil.getTableName());
        for (int i = tooLongTableName.length(); i < maxTableNameLength + 1; i++) {
            tooLongTableName.append("i");
        }
        HTableDescriptor tooLongNameDescriptor =
                new HTableDescriptor(TableName.valueOf(tooLongTableName.toString()));
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}");
        admin.createTable(tooLongNameDescriptor);
    }

    @Test
    public void testCreateTableWithTableNameStartsWithNumber() throws IOException {
        String numHeadName = 1 + HBaseTestUtil.getTableName();
        HTableDescriptor numHeadNameDescriptor = new HTableDescriptor(TableName.valueOf(numHeadName));
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern");
        admin.createTable(numHeadNameDescriptor);
    }

    @Test
    public void testCreateTableWithTableNameEqualsMaxLength() throws IOException {
        StringBuilder maxTableName = new StringBuilder(HBaseTestUtil.getTableName());
        for (int i = maxTableName.length(); i < maxTableNameLength; i++) {
            maxTableName.append("i");
        }
        HTableDescriptor tooLongNameDescriptor =
                new HTableDescriptor(TableName.valueOf(maxTableName.toString()));
        admin.createTable(tooLongNameDescriptor);
    }

    @Test
    public void testCreateTableWithCompressionAndTTLParam() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        final byte[] columnFamilyName = Constants.DEFAULT_FAMILY.getBytes();
        HColumnDescriptor createColumnDescriptor = new HColumnDescriptor(columnFamilyName);
        int timeToLive = 360;
        createColumnDescriptor.setTimeToLive(timeToLive);
        createColumnDescriptor.setCompressionType(Compression.Algorithm.SNAPPY);
        createDescriptor.addFamily(createColumnDescriptor);
        HBaseTestUtil.createTable(admin, createDescriptor, true);

        HTableDescriptor getDesc = admin.getTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor desc = getDesc.getFamily(columnFamilyName);
        assertEquals(Compression.Algorithm.SNAPPY, desc.getCompressionType());
        assertEquals(timeToLive, desc.getTimeToLive());
    }

    @Test
    public void testTableExists() throws IOException {
        logger.info("start to test tableExists function");
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);

        assertTrue(admin.tableExists(TableName.valueOf(tableName)));
        String tableNameNotExist = tableName + "not_exist";
        assertFalse(admin.tableExists(TableName.valueOf(tableNameNotExist)));
    }

    @Test
    public void testIsTableAvailableWithTableNameExceedsMaxLength() throws IOException {
        StringBuilder tooLongTableName = new StringBuilder(HBaseTestUtil.getTableName());
        for (int i = tooLongTableName.length(); i < maxTableNameLength + 1; i++) {
            tooLongTableName.append("i");
        }
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.isTableAvailable(TableName.valueOf(tooLongTableName.toString()));
    }

    @Test
    public void testIsTableAvailableWithTableNameStartsWithNumber() throws IOException {
        String numHeadName = 1 + HBaseTestUtil.getTableName();
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.isTableAvailable(TableName.valueOf(numHeadName));
    }

    @Test
    public void testGetDescriptor() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);

        HTableDescriptor descriptor = admin.getTableDescriptor(TableName.valueOf(tableName));
        assertEquals(tableName, descriptor.getTableName().toString());

        thrown.expect(TableNotFoundException.class);
        admin.getTableDescriptor(TableName.valueOf(HBaseTestUtil.getTableName()));
    }

    @Test
    public void testGetDescriptorWithTableNameExceedsMaxLength() throws IOException {
        StringBuilder tableNameExceedMaxLen = new StringBuilder(HBaseTestUtil.getTableName());
        for (int i = tableNameExceedMaxLen.length(); i < maxTableNameLength + 1; i++) {
            tableNameExceedMaxLen.append("v");
        }
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.getTableDescriptor(TableName.valueOf(tableNameExceedMaxLen.toString()));
    }

    @Test
    public void testGetDescriptorWithTableNameStartsWithNumber() throws IllegalArgumentException, IOException {
        String headNumName = 1 + HBaseTestUtil.getTableName();
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.getTableDescriptor(TableName.valueOf(headNumName));
    }

    @Test
    public void testListTables() throws IOException {
        List<String> tableNameList = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            String tableName = "table" + i;
            tableNameList.add(tableName);
        }
        boolean code = HBaseTestUtil.createTables(admin, tableNameList);
        assertTrue(code);

        HTableDescriptor[] descriptors = admin.listTables();
        for (String tableName : tableNameList) {
            boolean tableNameFound = false;
            for (HTableDescriptor descriptor : descriptors) {
                if (tableName.equals(descriptor.getNameAsString())) {
                    tableNameFound = true;
                    break;
                }
            }
            assertTrue(tableNameFound);
        }
    }

    @Test
    public void testListTablesWithPattern() throws IOException {
        List<String> tableNameList = new ArrayList<String>();

        List<String> tableNameList0 = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            String tableName = "table" + i;
            tableNameList0.add(tableName);
            tableNameList.add(tableName);
        }
        List<String> tableNameList1 = new ArrayList<String>();
        for (char c = 'a'; c <= 'j'; c++) {
            String tableName = "table" + c;
            tableNameList1.add(tableName);
            tableNameList.add(tableName);
        }
        List<String> tableNameList2 = new ArrayList<String>();
        for (char c = 'A'; c <= 'I'; c++) {
            String tableName = "table_1234_" + c;
            tableNameList2.add(tableName);
            tableNameList.add(tableName);
        }
        boolean succ = HBaseTestUtil.createTables(admin, tableNameList);
        assertTrue(succ);

        Pattern pattern = Pattern.compile("table\\d+");
        HTableDescriptor[] descriptors = admin.listTables(pattern);
        assertEquals(tableNameList0.size(), descriptors.length);
        for (String tableName : tableNameList0) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }

        String pattern0 = "table\\d+";
        descriptors = admin.listTables(pattern0);
        assertEquals(tableNameList0.size(), descriptors.length);
        for (String tableName : tableNameList0) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }

        pattern = Pattern.compile("table[a-z]");
        descriptors = admin.listTables(pattern);
        assertEquals(tableNameList1.size(), descriptors.length);
        for (String tableName : tableNameList1) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }
        pattern0 = "table[a-z]";
        descriptors = admin.listTables(pattern0);
        assertEquals(tableNameList1.size(), descriptors.length);
        for (String tableName : tableNameList1) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }

        pattern = Pattern.compile("^\\d");
        descriptors = admin.listTables(pattern);
        assertEquals(0, descriptors.length);
        pattern0 = "^\\d";
        descriptors = admin.listTables(pattern0);
        assertEquals(0, descriptors.length);

        pattern = Pattern.compile("^table\\w\\d+.*[A-Z]$");
        descriptors = admin.listTables(pattern);
        assertEquals(tableNameList2.size(), descriptors.length);
        for (String tableName : tableNameList2) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }
        pattern0 = "^table\\w\\d+.*[A-Z]$";
        descriptors = admin.listTables(pattern0);
        assertEquals(tableNameList2.size(), descriptors.length);
        for (String tableName : tableNameList2) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : descriptors) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }
        HBaseTestUtil.deleteTables(admin, true);
    }

    @Test
    public void testDeleteTable() throws IOException, InterruptedException {
        String tableName = HBaseTestUtil.getTableName();
        boolean code = HBaseTestUtil.createTable(admin, tableName);
        assertTrue(code);

        admin.deleteTable(TableName.valueOf(tableName));

        // check until table are deleted，check interval is 10s，max check count is 12
        int retryCount = 0;
        while (admin.isTableAvailable(TableName.valueOf(tableName)) && retryCount < 12) {
            logger.info("table is not available now, please wait a moment.");
            Thread.sleep(10000);
            ++retryCount;
        }
        assertFalse(admin.isTableAvailable(TableName.valueOf(tableName)));
    }

    @Test
    public void testDeleteTableWithPattern() throws IOException, InterruptedException {
        List<String> tableNameList = new ArrayList<String>();
        List<String> tableNameList0 = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            String tableName = "table" + i;
            tableNameList.add(tableName);
            tableNameList0.add(tableName);
        }

        List<String> tableNameList1 = new ArrayList<String>();
        for (char c = 'a'; c <= 'i'; c++) {
            String tableName = "table" + c;
            tableNameList.add(tableName);
            tableNameList1.add(tableName);
        }

        boolean code = HBaseTestUtil.createTables(admin, tableNameList);
        assertTrue(code);
        Pattern p1 = Pattern.compile("table\\d+");
        HTableDescriptor[] deleteDescriptors0 = admin.deleteTables(p1);
        assertEquals(tableNameList0.size(), deleteDescriptors0.length);
        for (String tableName : tableNameList0) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : deleteDescriptors0) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }

        Pattern p2 = Pattern.compile("table[a-z]");
        HTableDescriptor[] deleteDescriptors1 = admin.deleteTables(p2);
        assertEquals(tableNameList1.size(), deleteDescriptors1.length);
        for (String tableName : tableNameList1) {
            boolean tableNameExist = false;
            for (HTableDescriptor table : deleteDescriptors1) {
                if (tableName.equals(table.getNameAsString())) {
                    tableNameExist = true;
                    break;
                }
            }
            assertTrue(tableNameExist);
        }

        int retryCount = 0;
        while (admin.isTableAvailable(TableName.valueOf("tablei")) && retryCount < 12) {
            logger.info("table is not available now, please wait a moment.");
            Thread.sleep(10000);
            ++retryCount;
        }

        TableName[] tableNames0 = admin.listTableNames(p1);
        assertEquals(0, tableNames0.length);

        TableName[] tableNames1 = admin.listTableNames(p2);
        assertEquals(0, tableNames1.length);
    }

    @Test
    public void testDeleteTableWithTableNameExceedsMaxLength() throws IOException {
        StringBuilder tooLongTableName = new StringBuilder(HBaseTestUtil.getTableName());
        for (int i = tooLongTableName.length(); i < maxTableNameLength + 1; i++) {
            tooLongTableName.append("i");
        }
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.deleteTable(TableName.valueOf(tooLongTableName.toString()));
    }

    @Test
    public void testDeleteTableWithTableNameStartsWithNumber() throws IOException {
        String numHeadName = 1 + HBaseTestUtil.getTableName();
        thrown.expect(IOException.class);
        thrown.expectMessage("The TableName's value should match the pattern : [_a-zA-Z][_a-zA-Z0-9]{0,254}.");
        admin.deleteTable(TableName.valueOf(numHeadName));
    }

    @Test
    public void testModifyColumn() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        final byte[] columnFamilyName = Constants.DEFAULT_FAMILY.getBytes();
        HColumnDescriptor createColumnDescriptor = new HColumnDescriptor(columnFamilyName);
        createDescriptor.addFamily(createColumnDescriptor);
        boolean succ = HBaseTestUtil.createTable(admin, tableName, true);
        assertTrue(succ);

        HColumnDescriptor modColDesc = new HColumnDescriptor(columnFamilyName);
        int timeToLive = 360;
        modColDesc.setTimeToLive(timeToLive);
        modColDesc.setCompressionType(Compression.Algorithm.SNAPPY);
        admin.modifyColumn(TableName.valueOf(tableName), modColDesc);

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }

        HTableDescriptor getDesc = admin.getTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor desc = getDesc.getFamily(columnFamilyName);
        assertEquals(timeToLive, desc.getTimeToLive());
        assertEquals(Compression.Algorithm.SNAPPY, desc.getCompressionType());

        int timeToLiveDefault = TableStorageConstants.DEFAULT_LIVE_TIME;
        modColDesc.setTimeToLive(timeToLiveDefault);
        modColDesc.setCompressionType(Compression.Algorithm.NONE);
        admin.modifyColumn(TableName.valueOf(tableName), modColDesc);

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        HTableDescriptor getDesc2 = admin.getTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor desc2 = getDesc2.getFamily(columnFamilyName);
        assertEquals(360, desc2.getTimeToLive());
        assertEquals(Compression.Algorithm.NONE, desc2.getCompressionType());
    }

    @Test
    public void testModifyColumnTTLInvalid() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        final byte[] columnFamilyName = Constants.DEFAULT_FAMILY.getBytes();
        HColumnDescriptor createColumnDescriptor = new HColumnDescriptor(columnFamilyName);
        createDescriptor.addFamily(createColumnDescriptor);
        boolean succ = HBaseTestUtil.createTable(admin, tableName, true);
        assertTrue(succ);

        HColumnDescriptor modColDesc = new HColumnDescriptor(columnFamilyName);
        int timeToLive = -2;
        modColDesc.setTimeToLive(timeToLive);
        modColDesc.setCompressionType(Compression.Algorithm.SNAPPY);
        try {
            admin.modifyColumn(TableName.valueOf(tableName), modColDesc);
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage(),
                    containsString(
                            "The timeToLive's value cannot be a negative number other than DEFAULT_LIVE_TIME -1."));
        }

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        HTableDescriptor getDesc = admin.getTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor desc = getDesc.getFamily(columnFamilyName);
        assertEquals(desc.getCompressionType(), Compression.Algorithm.NONE);
        assertNotEquals(timeToLive, desc.getTimeToLive());
    }

    @Test
    public void testModifyColumnNotExist() throws IOException {
        String tableName = HBaseTestUtil.getTableName();
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        final byte[] columnFamilyName = Constants.DEFAULT_FAMILY.getBytes();
        HColumnDescriptor createColumnDescriptor = new HColumnDescriptor(columnFamilyName);
        createDescriptor.addFamily(createColumnDescriptor);
        boolean succ = HBaseTestUtil.createTable(admin, tableName, true);
        assertTrue(succ);

        final byte[] columnFamilyName1 = Bytes.toBytes(Constants.DEFAULT_FAMILY + "_not_exist");
        HColumnDescriptor modColDesc = new HColumnDescriptor(columnFamilyName1);
        int timeToLive = 360;
        modColDesc.setTimeToLive(timeToLive);
        modColDesc.setCompressionType(Compression.Algorithm.SNAPPY);

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Family name must be ");
        admin.modifyColumn(TableName.valueOf(tableName), modColDesc);
    }
}

