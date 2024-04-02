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

import org.junit.Assert;
import org.junit.Test;

public class TestBatchGetRowRequest {
    @Test
    public void testToJsonString() {
        BatchGetRowRequest batchGetRowRequest = new BatchGetRowRequest("table_test");
        GetRow get1 = new GetRow("rowkey1");
        get1.addCell("col1");
        get1.addCell("col2");
        GetRow get2 = new GetRow("rowkey2");
        get2.addCell("col1");
        get2.addCell("col2");

        batchGetRowRequest.addGetRow(get1);
        batchGetRowRequest.addGetRow(get2);

        String expectedJson = "{\"rows\":"
                + "[{\"rowkey\":\"rowkey1\",\"maxVersion\":1,\"cells\":[{\"column\":\"col1\"},{\"column\":\"col2\"}]},"
                + "{\"rowkey\":\"rowkey2\",\"maxVersion\":1,\"cells\":[{\"column\":\"col1\"},{\"column\":\"col2\"}]}]}";
        Assert.assertEquals(expectedJson, batchGetRowRequest.toJsonString());
    }

    @Test
    public void testToJsonStringWithMaxVersion() {
        BatchGetRowRequest batchGetRowRequest = new BatchGetRowRequest("table_test");
        GetRow get1 = new GetRow("rowkey1");
        get1.addCell("col1");
        get1.addCell("col2");
        get1.setMaxVersions(2);
        GetRow get2 = new GetRow("rowkey2");
        get2.addCell("col1");
        get2.addCell("col2");
        get2.setMaxVersions(3);

        batchGetRowRequest.addGetRow(get1);
        batchGetRowRequest.addGetRow(get2);

        String expectedJson = "{\"rows\":"
                + "[{\"rowkey\":\"rowkey1\",\"maxVersion\":2,\"cells\":[{\"column\":\"col1\"},{\"column\":\"col2\"}]},"
                + "{\"rowkey\":\"rowkey2\",\"maxVersion\":3,\"cells\":[{\"column\":\"col1\"},{\"column\":\"col2\"}]}]}";
        Assert.assertEquals(expectedJson, batchGetRowRequest.toJsonString());
    }

    @Test
    public void testToJsonStringNoGet() {
        BatchGetRowRequest batchGetRowRequest = new BatchGetRowRequest("table_test");

        String expectedJson = "{\"rows\":[]}";
        Assert.assertEquals(expectedJson, batchGetRowRequest.toJsonString());
    }
}
