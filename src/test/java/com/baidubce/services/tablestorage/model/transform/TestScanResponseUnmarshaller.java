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

import com.baidubce.BceClientException;
import com.baidubce.services.tablestorage.model.ScanResponse;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Unit test for ScanResponseUnmarshaller.
 */
public class TestScanResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{"
                + "    \"result\": ["
                + "        {"
                + "            \"rowkey\": \"k1%2F\","
                + "            \"cells\": ["
                + "                {"
                + "                    \"column\": \"c1\","
                + "                    \"value\": \"v1\""
                + "                },"
                + "                {"
                + "                    \"column\": \"c2\","
                + "                    \"value\": \"v2\""
                + "                }"
                + "            ]"
                + "        },"
                + "        {"
                + "            \"rowkey\": \"k2%2F\","
                + "            \"cells\": ["
                + "                {"
                + "                    \"column\": \"c3\","
                + "                    \"value\": \"v3\""
                + "                }"
                + "            ]"
                + "        }"
                + "    ],"
                + "    \"nextStartKey\": \"k3%2F\""
                + "}";

        ScanResponse result = new ScanResponse();
        ScanResponseUnmarshaller unmarshaller = new ScanResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("k3/", result.getNextStartKey());
        Assert.assertEquals(2, result.getResults().size());

        TableStorageResult res0 = result.getResults().get(0);
        Assert.assertEquals("k1/", res0.getRowkey());
        Assert.assertEquals(2, res0.getCells().size());
        Assert.assertEquals("c1", res0.getCells().get(0).getColumn());
        Assert.assertEquals("v1", res0.getCells().get(0).getValue());
        Assert.assertEquals("c2", res0.getCells().get(1).getColumn());
        Assert.assertEquals("v2", res0.getCells().get(1).getValue());

        TableStorageResult res1 = result.getResults().get(1);
        Assert.assertEquals("k2/", res1.getRowkey());
        Assert.assertEquals(1, res1.getCells().size());
        Assert.assertEquals("c3", res1.getCells().get(0).getColumn());
        Assert.assertEquals("v3", res1.getCells().get(0).getValue());
    }

    @Test
    public void testNoNextStartKeyUnmarshaller() {
        String resp = "{"
                + "    \"result\": ["
                + "        {"
                + "            \"rowkey\": \"k1%2F\","
                + "            \"cells\": ["
                + "                {"
                + "                    \"column\": \"c1\","
                + "                    \"value\": \"v1\""
                + "                }"
                + "            ]"
                + "        }"
                + "    ]"
                + "}";

        ScanResponse result = new ScanResponse();
        ScanResponseUnmarshaller unmarshaller = new ScanResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("", result.getNextStartKey());
        Assert.assertEquals(1, result.getResults().size());

        TableStorageResult res0 = result.getResults().get(0);
        Assert.assertEquals("k1/", res0.getRowkey());
        Assert.assertEquals(1, res0.getCells().size());
        Assert.assertEquals("c1", res0.getCells().get(0).getColumn());
        Assert.assertEquals("v1", res0.getCells().get(0).getValue());
    }

    @Test(expected = BceClientException.class)
    public void testUnmarshallerInvalidJson() throws Exception {
        String resp = "{invalid json}";

        ScanResponse result = new ScanResponse();
        ScanResponseUnmarshaller unmarshaller = new ScanResponseUnmarshaller(result);
        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        unmarshaller.unmarshall(in);
    }
}