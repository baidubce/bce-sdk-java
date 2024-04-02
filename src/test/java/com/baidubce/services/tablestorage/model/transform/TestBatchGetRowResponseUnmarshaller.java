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

import com.baidubce.services.tablestorage.model.BatchGetRowResponse;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class TestBatchGetRowResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{\"result\":[{\"rowkey\":\"rowkey_1\",\"cells\":[{\"column\":\"col1\",\"value\":\"val1\"}]},"
                + "{\"rowkey\":\"rowkey_2\",\"cells\":[{\"column\":\"col2\",\"value\":\"val2\"}]}]}";

        BatchGetRowResponse batchGetRowResponse = new BatchGetRowResponse();
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());

            BatchGetRowResponseUnmarshaller unmarshaller = new BatchGetRowResponseUnmarshaller(batchGetRowResponse);
            batchGetRowResponse = unmarshaller.unmarshall(in);
        } catch (Exception e) {

            e.printStackTrace();
        }

        List<TableStorageResult> results = batchGetRowResponse.getResults();

        Assert.assertEquals(2, results.size());
        Assert.assertEquals("rowkey_1", results.get(0).getRowkey());
        Assert.assertEquals("rowkey_2", results.get(1).getRowkey());

        Assert.assertEquals(1, results.get(0).getCells().size());
        Assert.assertEquals(1, results.get(1).getCells().size());

        Assert.assertEquals("col1", results.get(0).getCells().get(0).getColumn());
        Assert.assertEquals("col2", results.get(1).getCells().get(0).getColumn());

        Assert.assertEquals("val1", results.get(0).getCells().get(0).getValue());
        Assert.assertEquals("val2", results.get(1).getCells().get(0).getValue());
    }
}
