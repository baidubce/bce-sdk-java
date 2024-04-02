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
import com.baidubce.services.tablestorage.model.ListTablesRequest;
import com.baidubce.services.tablestorage.model.ShowTableRequest;
import org.junit.Test;

public class TestInputCheck {

    @Test (expected = BceClientException.class)
    public void testEmptyInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "");
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testLongInstanceName() {
        StringBuffer instanceName = new StringBuffer();
        for (int i = 0; i < 256; i++) {
            instanceName.append("a");
        }
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), instanceName.toString());
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testSpecialCharactersInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "//");
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testDigitalInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "70_A");
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testBaiduInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test_baidu_A");
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testBceInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test_bce_A");
        client.listTables(new ListTablesRequest());
    }

    @Test (expected = BceClientException.class)
    public void testTablestorageInstanceName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test_tablestorage_A");
        client.listTables(new ListTablesRequest());
    }


    @Test (expected = BceClientException.class)
    public void testEmptyTableName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test");
        client.showTable(new ShowTableRequest(""));
    }

    @Test (expected = BceClientException.class)
    public void testLongTableName() {
        StringBuffer tableName = new StringBuffer();
        for (int i = 0; i < 256; i++) {
            tableName.append("a");
        }
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test");
        client.showTable(new ShowTableRequest(tableName.toString()));
    }

    @Test (expected = BceClientException.class)
    public void testSpecialCharactersTableName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test");
        client.showTable(new ShowTableRequest("//"));
    }

    @Test (expected = BceClientException.class)
    public void testDigitalTableName() {
        TableStorageClient client = new TableStorageClient(new BceClientConfiguration(), "test");
        client.showTable(new ShowTableRequest("70_A"));
    }
}