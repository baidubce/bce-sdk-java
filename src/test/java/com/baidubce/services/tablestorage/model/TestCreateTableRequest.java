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

package com.baidubce.services.tablestorage.model;

import com.baidubce.BceClientException;
import org.junit.Assert;
import org.junit.Test;

public class TestCreateTableRequest {
    @Test
    public void testToJsonString() {
        TableOption tableOption = new TableOption();

        CreateTableRequest createTableRequest = new CreateTableRequest("table_test", tableOption);

        String expectedJson = "{\"tableVersion\":0,\"compressType\":\"NONE\",\"ttl\":0,\"maxVersion\":1}";
        Assert.assertEquals(expectedJson, createTableRequest.toJsonString());
    }

    @Test
    public void testToJsonStringNoDefaultFieldValue() {
        TableOption tableOption = new TableOption();

        tableOption.setTableVersion(0);
        tableOption.setMaxVersions(3);
        tableOption.setCompressType(CompressType.SNAPPY_ALL);
        tableOption.setTimeToLive(100);
        CreateTableRequest createTableRequest = new CreateTableRequest("table_test", tableOption);

        String expectedJson = "{\"tableVersion\":0,\"compressType\":\"SNAPPY_ALL\",\"ttl\":100,\"maxVersion\":3}";
        Assert.assertEquals(expectedJson, createTableRequest.toJsonString());
    }

    @Test(expected = BceClientException.class)
    public void testToJsonStringWithInvalidTableVersion() throws BceClientException {
        TableOption tableOption = new TableOption();

        tableOption.setTableVersion(100);
        CreateTableRequest request = new CreateTableRequest("table_test", tableOption);
        request.toJsonString();
    }
}