/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.tablestorage;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.CreateTableRequest;
import com.baidubce.services.tablestorage.model.CreateTableResponse;
import com.baidubce.services.tablestorage.model.DropTableRequest;
import com.baidubce.services.tablestorage.model.DropTableResponse;
import com.baidubce.services.tablestorage.model.ListTablesRequest;
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.ShowTableRequest;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.TableOption;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.services.tablestorage.model.UpdateTableRequest;
import com.baidubce.services.tablestorage.model.UpdateTableResponse;

/**
 * Example for Table Operations.
 */
public class TableOperationsExample {
    private static final String TABLE_NAME = "table_demo";
    private static final String INSTANCE_NAME = "instance_demo";

    private TableStorageClient tableStorageClient;

    public TableOperationsExample(TableStorageClient client) {
        this.tableStorageClient = client;
    }

    private void createTable() {
        TableOption createTableOption = new TableOption();
        createTableOption.setTableVersion(TableStorageConstants.CREATE_TABLE_VERSION);
        createTableOption.setTimeToLive(TableStorageConstants.FORERVER_LIVE_TIME);
        createTableOption.setCompressType(CompressType.NONE);
        createTableOption.setStorageType(TableStorageConstants.STORAGE_TYPE_COMMON_PERFORMANCE);
        createTableOption.setMaxVersions(5);
        CreateTableRequest createTableRequest = new CreateTableRequest(TABLE_NAME, createTableOption);
        CreateTableResponse result = tableStorageClient.createTable(createTableRequest);
        System.out.println("result:" + result.toString());
    }

    private void updateTable() {
        ShowTableRequest showTableRequest = new ShowTableRequest(TABLE_NAME);
        ShowTableResponse showTableResponse = tableStorageClient.showTable(showTableRequest);
        long tableVersion = showTableResponse.getTableVersion();

        TableOption updateTableOption = new TableOption();
        updateTableOption.setTableVersion(tableVersion);
        updateTableOption.setTimeToLive(24 * 3600);
        updateTableOption.setCompressType(CompressType.SNAPPY_ALL);
        updateTableOption.setMaxVersions(7);
        UpdateTableRequest updateTableRequest = new UpdateTableRequest(TABLE_NAME, updateTableOption);

        UpdateTableResponse result = tableStorageClient.updateTable(updateTableRequest);
        System.out.println("result:" + result.toString());
    }

    private void listTables() {
        ListTablesRequest listTablesRequest = new ListTablesRequest();
        ListTablesResponse result = tableStorageClient.listTables(listTablesRequest);
        System.out.println("result:" + result.toString());
    }

    private void waitTableAvailable() {
        ListTablesRequest listTablesRequest = new ListTablesRequest();
        ListTablesResponse.TableInfo info;
        while ((info = tableStorageClient.listTables(listTablesRequest).getTableInfo(TABLE_NAME)) == null) {
            System.out.println("cannot get table " + TABLE_NAME + " now, please wait a moment.");
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (info.getTableState() != TableState.Normal) {
            TableState state;
            while ((state = tableStorageClient.showTableState(TABLE_NAME)) != TableState.Normal) {
                System.out.println("table " + TABLE_NAME + " with state" + state
                        + " is not available, please wait a moment.");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showTable() {
        ShowTableRequest showTableRequest = new ShowTableRequest(TABLE_NAME);
        ShowTableResponse result = tableStorageClient.showTable(showTableRequest);
        System.out.println("result:" + result.toString());
    }

    private void dropTable() {
        DropTableRequest dropTableRequest = new DropTableRequest(TABLE_NAME);
        DropTableResponse result = tableStorageClient.dropTable(dropTableRequest);
        System.out.println("result:" + result.toString());
    }

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("Getting Started with Baidu TableStorage");
        System.out.println("===========================================\n");

        BceCredentials credentials = null;
        try {
            credentials = new DefaultBceCredentials("xxxxxx", "xxxxxx");
        } catch (Exception e) {
            throw new BceClientException(
                    "Cannot load the credentials from the credential profiles file. "
                            + "Please make sure that your credentials file is at the correct "
                            + "and is in valid format.", e);
        }

        BceClientConfiguration conf = new BceClientConfiguration();

        conf.setCredentials(credentials);
        conf.setEndpoint("bts.xx.baidubce.com");

        TableStorageClient client = new TableStorageClient(conf, INSTANCE_NAME);
        TableOperationsExample example = new TableOperationsExample(client);

        try {
            // create table
            example.createTable();

            example.waitTableAvailable();

            // list tables
            example.listTables();

            // update table
            example.updateTable();

            // show table
            example.showTable();
        } catch (BceServiceException bse) {
            System.out.println("Caught an tablestorage, which means your request made it "
                    + "to tablestorage, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message: " + bse.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + bse.getStatusCode());
            System.out.println("tablestorage Error Code:   " + bse.getErrorCode());
            System.out.println("Error Type:       " + bse.getErrorType());
            System.out.println("Error Type:       " + bse.getErrorMessage());
            System.out.println("Request ID:       " + bse.getRequestId());
            bse.printStackTrace();
        } catch (BceClientException bce) {
            System.out.println("Caught an TableStorageClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with TableStorage, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + bce.getMessage());
            bce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // drop table
                example.dropTable();
            } catch (BceServiceException bse) {
                System.out.println("Caught an tablestorage, which means your request made it "
                        + "to tablestorage, but was rejected with an error responseContent for some reason.");
                System.out.println("Error Message: " + bse.getMessage());
                System.out.println("HTTP ErrorDescription Code: " + bse.getStatusCode());
                System.out.println("tablestorage Error Code:   " + bse.getErrorCode());
                System.out.println("Error Type:       " + bse.getErrorType());
                System.out.println("Request ID:       " + bse.getRequestId());
                bse.printStackTrace();
            } catch (BceClientException bce) {
                System.out.println("Caught an TableStorageClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with TableStorage, "
                        + "such as not being able to access the network.");
                System.out.println("Error Message: " + bce.getMessage());
                bce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            client.shutdown();
            System.out.println("end now");
        }

    }
}