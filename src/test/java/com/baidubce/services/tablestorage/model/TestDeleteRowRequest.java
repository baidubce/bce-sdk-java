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

public class TestDeleteRowRequest {
    @Test
    public void testToJsonString() {
        DeleteRowRequest deleteRowRequest = new DeleteRowRequest("table_test", "rowkey1");

        String expectedJson = "{\"rowkey\":\"rowkey1\",\"cells\":[]}";
        Assert.assertEquals(expectedJson, deleteRowRequest.toJsonString());
    }

    @Test
    public void testToJsonStringWithColumns() {
        DeleteRowRequest deleteRowRequest = new DeleteRowRequest("table_test", "rowkey1");
        deleteRowRequest.addCell("col1");
        deleteRowRequest.addCell("col2");

        String expectedJson = "{\"rowkey\":\"rowkey1\",\"cells\":[{\"column\":\"col1\"},{\"column\":\"col2\"}]}";
        Assert.assertEquals(expectedJson, deleteRowRequest.toJsonString());
    }
}
