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

/**
 * @file Demo.java
 * @date 2019/02/26 11:02:23
 * @brief
 */

package com.baidubce.services.tablestoragehbaseclient.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
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
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tablestorage Client Demo
 * @date 2019/02/26 11:02:23
 */
public class TestTablestorage {
    private static final String TABLE_NAME = "table_demo";
    private static final byte[] ROW_KEY = Bytes.toBytes("row_0");
    private static final byte[] COLUMN_FAMILY_NAME = Bytes.toBytes("cf0");
    private static final byte[] COLUMN_NAME = Bytes.toBytes("col_0");
    private static final byte[] DEFAULT_VALUE = Bytes.toBytes("value_0");


    @Test
    public void testTablestorage() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
        BufferedMutator bufferedMutator = connection.getBufferedMutator(TableName.valueOf(TABLE_NAME));
        RegionLocator regionLocator = connection.getRegionLocator(TableName.valueOf(TABLE_NAME));

        try {
            Assert.assertEquals(false, admin.isTableAvailable(TableName.valueOf(TABLE_NAME)));

            int timeToLive = 24 * 3600;
            HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
            HColumnDescriptor createColumnDescriptor = new HColumnDescriptor(COLUMN_FAMILY_NAME);
            createColumnDescriptor.setTimeToLive(timeToLive);
            createColumnDescriptor.setCompressionType(Compression.Algorithm.SNAPPY);
            createDescriptor.addFamily(createColumnDescriptor);
            admin.createTable(createDescriptor);
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (!admin.isTableAvailable(TableName.valueOf(TABLE_NAME))) {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Assert.assertEquals(true, admin.isTableAvailable(TableName.valueOf(TABLE_NAME)));

            HTableDescriptor descriptor = admin.getTableDescriptor(TableName.valueOf(TABLE_NAME));
            Assert.assertEquals(1, descriptor.getFamilies().size());
            for (HColumnDescriptor columnDescriptor : descriptor.getFamilies()) {
                Assert.assertEquals(Compression.Algorithm.SNAPPY, columnDescriptor.getCompressionType());
                Assert.assertEquals(timeToLive, columnDescriptor.getTimeToLive());
            }

            HColumnDescriptor modifyColumnDescriptor = new HColumnDescriptor(COLUMN_FAMILY_NAME);
            modifyColumnDescriptor.setTimeToLive(HConstants.FOREVER);
            modifyColumnDescriptor.setCompressionType(Compression.Algorithm.NONE);
            admin.modifyColumn(TableName.valueOf(TABLE_NAME), modifyColumnDescriptor);

            while (true) {
                HTableDescriptor descriptorMod = admin.getTableDescriptor(TableName.valueOf(TABLE_NAME));
                Assert.assertEquals(1, descriptor.getFamilies().size());
                boolean retry = false;
                for (HColumnDescriptor columnDescriptor : descriptorMod.getFamilies()) {
                    if (Compression.Algorithm.NONE != columnDescriptor.getCompressionType()) {
                        try {
                            Thread.sleep(10 * 1000);
                            retry = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (0 != columnDescriptor.getTimeToLive()) {
                        try {
                            Thread.sleep(10 * 1000);
                            retry = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (retry) {
                    continue;
                }
                for (HColumnDescriptor columnDescriptor : descriptorMod.getFamilies()) {
                    Assert.assertEquals(Compression.Algorithm.NONE, columnDescriptor.getCompressionType());
                    Assert.assertEquals(0, columnDescriptor.getTimeToLive());
                }
                break;
            }

            TableName[] tableNames = admin.listTableNames();
            Assert.assertEquals(1, tableNames.length);
            Assert.assertEquals(TABLE_NAME, tableNames[0].getNameAsString());

            tableNames = admin.listTableNames("Table.*");
            Assert.assertEquals(0, tableNames.length);
            tableNames = admin.listTableNames(".*demo");
            Assert.assertEquals(1, tableNames.length);
            Assert.assertEquals(TABLE_NAME, tableNames[0].getNameAsString());

            Put put = new Put(ROW_KEY);
            put.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME, DEFAULT_VALUE);
            table.put(put);
            Get get = new Get(ROW_KEY);
            get.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME);
            Result result = table.get(get);
            Assert.assertEquals(Bytes.toString(DEFAULT_VALUE),
                                Bytes.toString(result.getValue(COLUMN_FAMILY_NAME, COLUMN_NAME)));

            int rowNum = 50;
            int colNum = 10;
            List<Put> putList = new ArrayList<Put>();
            for (int i = 1; i <= rowNum; i++) {
                Put put1 = new Put(Bytes.toBytes("row_" + i));
                for (int j = 0; j < colNum; j++) {
                    put1.addColumn(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j),
                            Bytes.toBytes("value_" + i + "_" + j));
                }
                putList.add(put1);
            }
            table.put(putList);

            List<Get> getList = new ArrayList<Get>();
            for (int i = 1; i <= rowNum; i++) {
                Get get1 = new Get(Bytes.toBytes("row_" + i));
                for (int j = 0; j < colNum; j++) {
                    get1.addColumn(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j));
                }
                getList.add(get1);
            }
            Result[] results = table.get(getList);
            Assert.assertEquals(rowNum, results.length);
            for (int i = 1; i <= rowNum; i++) {
                Assert.assertEquals("row_" + i, Bytes.toString(results[i - 1].getRow()));
                for (int j = 0; j < colNum; j++) {
                    Assert.assertEquals("value_" + i + "_" + j,
                            Bytes.toString(results[i - 1].getValue(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j))));
                }
            }

            Delete delete = new Delete(ROW_KEY);
            delete.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME);
            table.delete(delete);
            result = table.get(get);
            Assert.assertEquals(null, result.getRow());

            Scan scan = new Scan();
            scan.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME);
            ResultScanner scanner = table.getScanner(scan);

            // order rowkey by dict order
            ArrayList<String> dictOrderRowkey = new ArrayList<String>();
            for (int i = 1; i <= rowNum; ++i) {
                dictOrderRowkey.add(Integer.toString(i));
            }
            Collections.sort(dictOrderRowkey);
            try {
                int index = 0;
                for (Result row : scanner) {
                    Assert.assertEquals("row_" + dictOrderRowkey.get(index++), Bytes.toString(row.getRow()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }

            List<Delete> deleteList = new ArrayList<Delete>();
            for (int i = 1; i <= rowNum; i++) {
                Delete delete1 = new Delete(Bytes.toBytes("row_" + i));
                for (int j = 0; j < colNum; j++) {
                    delete1.addColumn(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j));
                }
                deleteList.add(delete1);
            }
            table.delete(deleteList);

            results = table.get(getList);
            Assert.assertEquals(rowNum, results.length);
            for (int i = 0; i < colNum; i++) {
                Assert.assertEquals(null, results[i].getRow());
            }

            Put put1 = new Put(ROW_KEY);
            put1.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME, DEFAULT_VALUE);
            bufferedMutator.mutate(put1);
            bufferedMutator.flush();
            result = table.get(get);
            Assert.assertEquals(Bytes.toString(DEFAULT_VALUE),
                                Bytes.toString(result.getValue(COLUMN_FAMILY_NAME, COLUMN_NAME)));

            Delete delete2 = new Delete(ROW_KEY);
            delete2.addColumn(COLUMN_FAMILY_NAME, COLUMN_NAME);
            bufferedMutator.mutate(delete2);
            bufferedMutator.flush();
            result = table.get(get);
            Assert.assertEquals(null, result.getRow());

            List<Mutation> mutationList = new ArrayList<Mutation>();
            for (int i = 1; i <= rowNum; i++) {
                Put putMutation = new Put(Bytes.toBytes("row_" + i));
                for (int j = 0; j < colNum; j++) {
                    putMutation.addColumn(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j),
                            Bytes.toBytes("value_" + i + "_" + j));
                }
                mutationList.add(putMutation);
            }
            bufferedMutator.mutate(mutationList);
            bufferedMutator.flush();

            results = table.get(getList);
            Assert.assertEquals(rowNum, results.length);
            for (int i = 1; i <= rowNum; i++) {
                Assert.assertEquals("row_" + i, Bytes.toString(results[i - 1].getRow()));
                for (int j = 0; j < colNum; j++) {
                    Assert.assertEquals("value_" + i + "_" + j,
                            Bytes.toString(results[i - 1].getValue(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j))));
                }
            }

            for (int i = 1; i <= rowNum; i++) {
                Delete deleteMutation = new Delete(Bytes.toBytes("row_" + i));
                for (int j = 0; j < colNum; j++) {
                    deleteMutation.addColumn(COLUMN_FAMILY_NAME, Bytes.toBytes("col_" + j));
                }
                mutationList.add(deleteMutation);
            }
            bufferedMutator.mutate(mutationList);
            bufferedMutator.flush();
            results = table.get(getList);
            Assert.assertEquals(rowNum, results.length);
            for (int i = 0; i < colNum; i++) {
                Assert.assertEquals(null, results[i].getRow());
            }

            admin.deleteTable(TableName.valueOf(TABLE_NAME));
            while (admin.isTableAvailable(TableName.valueOf(TABLE_NAME))) {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Assert.assertEquals(false, admin.isTableAvailable(TableName.valueOf(TABLE_NAME)));

            regionLocator.close();
            bufferedMutator.close();
            table.close();
            admin.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
