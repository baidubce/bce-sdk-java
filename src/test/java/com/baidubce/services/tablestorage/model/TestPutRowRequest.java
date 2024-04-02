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

public class TestPutRowRequest {
    @Test
    public void testToJsonString() {
        PutRowRequest putRowRequest = new PutRowRequest("table_test", "rowkey1");

        String expectedJson = "{\"rowkey\":\"rowkey1\",\"cells\":[]}";
        Assert.assertEquals(expectedJson, putRowRequest.toJsonString());
    }

    @Test
    public void testToJsonStringWithColumns() {
        PutRowRequest putRowRequest = new PutRowRequest("table_test", "rowkey1");
        putRowRequest.addCell("col1", "val1");
        putRowRequest.addCell("col2", "val2");

        String expectedJson = "{\"rowkey\":\"rowkey1\",\"cells\":[{\"column\":\"col1\",\"value\":\"val1\"},"
                + "{\"column\":\"col2\",\"value\":\"val2\"}]}";
        Assert.assertEquals(expectedJson, putRowRequest.toJsonString());
    }
}
