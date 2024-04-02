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

import com.baidubce.services.tablestorage.model.TableStorageCell;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import com.baidubce.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestTableStorageResultUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{\"rowkey\":\"test\",\"cells\":[{\"column\":\"col0\",\"value\":\"val0\"},"
                + "{\"column\":\"col1\",\"value\":\"val1\"}]}";


        TableStorageResultUnmarshaller unmarshaller = new TableStorageResultUnmarshaller();
        TableStorageResult result = null;
        try {
            result = unmarshaller.unmarshall(JsonUtils.jsonNodeOf(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(null, result);
        if (result != null) {
            Assert.assertEquals("test", result.getRowkey());

            List<TableStorageCell> cells = result.getCells();
            Assert.assertEquals(2, cells.size());
            Assert.assertEquals("col0", cells.get(0).getColumn());
            Assert.assertEquals("val0", cells.get(0).getValue());
            Assert.assertEquals("col1", cells.get(1).getColumn());
            Assert.assertEquals("val1", cells.get(1).getValue());
        }
    }
}
