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

package com.baidubce.services.tablestorage.model.transform;

import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.services.tablestorage.model.TableStorageCell;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class TestGetRowResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{\"result\":[{\"rowkey\":\"rowkey_1\",\"cells\":[{\"column\":\"col1\",\"value\":\"val1\"},"
                +  "{\"column\":\"col2\",\"value\":\"val2\"}]}]}";

        GetRowResponse rowResponse = new GetRowResponse();
        GetRowResponseUnmarshaller unmarshaller = new GetRowResponseUnmarshaller(rowResponse);

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            rowResponse = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TableStorageResult result = rowResponse.getResult();
        List<TableStorageCell> cells = result.getCells();

        Assert.assertEquals("rowkey_1", result.getRowkey());
        Assert.assertEquals(2, cells.size());
        Assert.assertEquals("col1", cells.get(0).getColumn());
        Assert.assertEquals("val1", cells.get(0).getValue());
        Assert.assertEquals("col2", cells.get(1).getColumn());
        Assert.assertEquals("val2", cells.get(1).getValue());
    }

    @Test
    public void testNullResultUnmarshaller() {
        String resp = "{}";

        GetRowResponse rowResponse = new GetRowResponse();
        GetRowResponseUnmarshaller unmarshaller = new GetRowResponseUnmarshaller(rowResponse);

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            rowResponse = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TableStorageResult result = rowResponse.getResult();
        Assert.assertNull(result);
    }
}
