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
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.TableState;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Unit test for ShowTableResponseUnmarshaller.
 */
public class TestShowTableResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{"
                + "    \"instance\":\"my_ins\","
                + "    \"tableName\":\"xxx\","
                + "    \"tableState\":\"Normal\","
                + "    \"tableVersion\":1552272890193677,"
                + "    \"createTime\":\"2019-03-11T02:54:50Z\","
                + "    \"compressType\":\"NONE\","
                + "    \"ttl\":0"
                + "}";

        ShowTableResponse result = new ShowTableResponse();
        com.baidubce.services.tablestorage.model.transform.ShowTableResponseUnmarshaller unmarshaller =
                new ShowTableResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("2019-03-11T02:54:50Z", result.getCreateTime());
        Assert.assertEquals(0, result.getTimeToLive());
        Assert.assertEquals(CompressType.NONE, result.getCompressType());
        Assert.assertEquals(1552272890193677L, result.getTableVersion());
        Assert.assertEquals(TableState.Normal, result.getTableState());
        Assert.assertEquals("xxx", result.getTableName());
        Assert.assertEquals("my_ins", result.getInstanceName());
        Assert.assertEquals(1, result.getMaxVersions());
    }

    @Test
    public void testWithMaxVersionJsonUnmarshaller() {
        String resp = "{"
                + "    \"instance\":\"my_ins\","
                + "    \"tableName\":\"xxx\","
                + "    \"tableState\":\"Normal\","
                + "    \"tableVersion\":1552272890193677,"
                + "    \"createTime\":\"2019-03-11T02:54:50Z\","
                + "    \"compressType\":\"NONE\","
                + "    \"ttl\":0,"
                + "    \"maxVersions\":5"
                + "}";

        ShowTableResponse result = new ShowTableResponse();
        com.baidubce.services.tablestorage.model.transform.ShowTableResponseUnmarshaller unmarshaller =
                new ShowTableResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("2019-03-11T02:54:50Z", result.getCreateTime());
        Assert.assertEquals(0, result.getTimeToLive());
        Assert.assertEquals(CompressType.NONE, result.getCompressType());
        Assert.assertEquals(1552272890193677L, result.getTableVersion());
        Assert.assertEquals(TableState.Normal, result.getTableState());
        Assert.assertEquals("xxx", result.getTableName());
        Assert.assertEquals("my_ins", result.getInstanceName());
        Assert.assertEquals(5, result.getMaxVersions());
    }

    @Test(expected = BceClientException.class)
    public void testUnmarshallerInvalidJson() throws Exception {
        String resp = "{invalid json}";

        ShowTableResponse result = new ShowTableResponse();
        ShowTableResponseUnmarshaller unmarshaller = new ShowTableResponseUnmarshaller(result);
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
