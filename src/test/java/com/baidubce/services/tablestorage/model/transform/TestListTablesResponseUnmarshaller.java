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
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.ListTablesResponse.TableInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class TestListTablesResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{\"tables\":[{"
                + "\"tableName\":\"xxx\",\"tableState\":\"Normal\",\"tableVersion\":1552272890193677,"
                + "\"createTime\":\"2019-03-11T02:54:50Z\"},"
                + "{\"tableName\":\"yb_table\",\"tableState\":\"Normal\",\"tableVersion\":1544674239909374,"
                + "\"createTime\":\"2018-12-13T04:10:39Z\"}]}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TableInfo> tables = result.getTables();
        Assert.assertEquals(2, tables.size());
    }

    @Test
    public void testUnmarshallerNoTable() {
        String resp = "{\"tables\":[]}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<TableInfo> tables = result.getTables();
        Assert.assertEquals(0, tables.size());
    }

    @Test
    public void testUnmarshallerWrongJson() {
        String resp = "{\"wrong\":\"json\"}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(0, result.getTables().size());
    }

    @Test(expected = BceClientException.class)
    public void testUnmarshallerInvalidJson() throws Exception {
        String resp = "{invalid json}";

        ListTablesResponse result = new ListTablesResponse();
        ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(result);
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
