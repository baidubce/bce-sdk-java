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
package com.baidubce.services.tablestoragehbaseclient.util;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tablestorage Client HBaseTestUtil
 *
 * @date 2019/05/16 15:02:23
 */
public class HBaseTestUtil {
    private static Logger logger = LoggerFactory.getLogger(HBaseTestUtil.class);

    public static String getTableName() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return "Table" + t;
    }

    public static String getStringWithChar(int length, char charToFill) {
        char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = charToFill;
        }
        return new String(array);
    }

    public static boolean waitUntilAvailable(Admin admin, String tableName)
            throws IOException {
        boolean tableAvailable = true;
        for (int retryCount = 0; retryCount < 12; ++retryCount) {
            boolean exists = admin.tableExists(TableName.valueOf(tableName));
            if (exists) {
                tableAvailable = admin.isTableAvailable(TableName.valueOf(tableName));
                if (tableAvailable) {
                    break;
                }
            }
            logger.warn("table is not available now, please wait a moment.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
        if (tableAvailable) {
            logger.info("table is available now, tableName:" + tableName);
        } else {
            logger.error("table is not available now, tableName:" + tableName);
        }
        return tableAvailable;
    }

    public static boolean waitUntilAvailable(Admin admin, List<String> tableNameList)
            throws IOException {
        boolean allTableAvailable = true;
        for (int retryCount = 0; retryCount < 12; ++retryCount) {
            allTableAvailable = true;
            for (String tableName : tableNameList) {
                if (!admin.isTableAvailable(TableName.valueOf(tableName))) {
                    allTableAvailable = false;
                    break;
                }
            }
            if (allTableAvailable) {
                break;
            }
            logger.warn("table is not available now, please wait a moment.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
        if (allTableAvailable) {
            logger.info("all tables are available now.");
        } else {
            logger.error("some tables are still not available now.");
        }
        return allTableAvailable;
    }

    public static boolean waitUntilNotExist(Admin admin, String tableName)
            throws IOException {
        boolean tableNotExist = true;
        for (int retryCount = 0; retryCount < 12; ++retryCount) {
            tableNotExist = admin.tableExists(TableName.valueOf(tableName));
            if (tableNotExist) {
                break;
            }
            logger.warn("table is not available now, please wait a moment.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
        if (tableNotExist) {
            logger.info("table is not existing now, tableName:" + tableName);
        } else {
            logger.error("table is still existing now, tableName:" + tableName);
        }
        return tableNotExist;
    }

    public static boolean waitUntilNotExist(Admin admin, List<String> tableNameList)
            throws IOException {
        boolean allTableNotExist = true;
        for (int retryCount = 0; retryCount < 12; ++retryCount) {
            allTableNotExist = true;
            for (String tableName : tableNameList) {
                if (!admin.tableExists(TableName.valueOf(tableName))) {
                    allTableNotExist = false;
                    break;
                }
            }
            if (allTableNotExist) {
                break;
            }
            logger.warn("table is not available now, please wait a moment.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
        }
        if (allTableNotExist) {
            logger.info("all tables are not existing now.");
        } else {
            logger.error("some tables are still existing now.");
        }
        return allTableNotExist;
    }

    public static boolean createTable(Admin admin, HTableDescriptor createDescriptor, boolean waitUntilAvailable)
            throws IOException {
        admin.createTable(createDescriptor);
        if (!waitUntilAvailable) {
            return true;
        }
        return waitUntilAvailable(admin, createDescriptor.getTableName().toString());
    }

    public static boolean createTable(Admin admin, String tableName, boolean waitUntilAvailable) throws IOException {
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        return createTable(admin, createDescriptor, waitUntilAvailable);
    }

    public static boolean createTable(Admin admin, String tableName) throws IOException {
        HTableDescriptor createDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        return createTable(admin, createDescriptor, true);
    }

    public static boolean createTables(Admin admin, List<String> tableNameList, boolean waitUntilAvailable)
            throws IOException {
        List<HTableDescriptor> hTableDescriptorList = new ArrayList<HTableDescriptor>();
        for (String tableName : tableNameList) {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            hTableDescriptorList.add(hTableDescriptor);
        }

        for (HTableDescriptor hTableDescriptor : hTableDescriptorList) {
            admin.createTable(hTableDescriptor);
        }
        if (!waitUntilAvailable) {
            return true;
        }
        return waitUntilAvailable(admin, tableNameList);
    }

    public static boolean createTables(Admin admin, List<String> tableNameList) throws IOException {
        return createTables(admin, tableNameList, true);
    }

    public static boolean deleteTables(Admin admin, List<String> tableNameList, boolean waitUntilFinish)
            throws IOException {
        for (String tableName : tableNameList) {
            admin.deleteTable(TableName.valueOf(tableName));
        }
        if (!waitUntilFinish) {
            return true;
        }
        return waitUntilAvailable(admin, tableNameList);
    }

    public static boolean deleteTables(Admin admin, List<String> tableNameList) throws IOException {
        return deleteTables(admin, tableNameList, true);
    }

    public static boolean deleteTables(Admin admin, boolean waitUntilFinish) throws IOException {
        TableName[] tableNames;
        tableNames = admin.listTableNames();
        List<String> tableNameList = new ArrayList<String>();
        for (TableName tableName : tableNames) {
            tableNameList.add(tableName.getNameAsString());
        }
        return deleteTables(admin, tableNameList, waitUntilFinish);
    }
}
