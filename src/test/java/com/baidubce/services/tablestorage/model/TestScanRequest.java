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

public class TestScanRequest {
    @Test
    public void testToJsonString() {
        ScanRequest scanRequest = new ScanRequest("table_test");

        String expectedJson = "{\"maxVersion\":1,\"selector\":[]}";
        Assert.assertEquals(expectedJson, scanRequest.toJsonString());
    }

    @Test
    public void testToJsonStringWithPart() {
        ScanRequest scanRequest = new ScanRequest("table_test");
        scanRequest.setStartRowkey("rowkey1", true);
        scanRequest.addSelector("col1");
        scanRequest.setMaxVersions(2);

        String expectedJson = "{\"maxVersion\":2,"
                + "\"startRowkey\":\"rowkey1\",\"includeStart\":true,"
                + "\"selector\":[{\"column\":\"col1\"}]}";
        Assert.assertEquals(expectedJson, scanRequest.toJsonString());
    }

    @Test
    public void testToJsonStringWithAll() {
        ScanRequest scanRequest = new ScanRequest("table_test");
        scanRequest.setStartRowkey("rowkey1", true);
        scanRequest.setStopRowkey("rowkey2", false);
        scanRequest.addSelector("col1");
        scanRequest.addSelector("col2");
        scanRequest.setLimit(1);
        scanRequest.setMaxVersions(2);

        String expectedJson = "{\"maxVersion\":2,"
                              + "\"startRowkey\":\"rowkey1\",\"includeStart\":true,"
                              + "\"stopRowkey\":\"rowkey2\",\"includeStop\":false,"
                              + "\"selector\":[{\"column\":\"col1\"},{\"column\":\"col2\"}],"
                              + "\"limit\":1}";
        Assert.assertEquals(expectedJson, scanRequest.toJsonString());
    }

}
