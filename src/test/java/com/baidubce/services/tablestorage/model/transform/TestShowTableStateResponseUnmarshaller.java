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
import com.baidubce.services.tablestorage.model.ShowTableStateResponse;
import com.baidubce.services.tablestorage.model.TableState;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Unit test for ShowTableStateResponseUnmarshaller.
 */
public class TestShowTableStateResponseUnmarshaller {
    @Test
    public void testUnmarshaller() {
        String resp = "{\"tableState\":\"Normal\"}";

        ShowTableStateResponse result = new ShowTableStateResponse();
        com.baidubce.services.tablestorage.model.transform.ShowTableStateResponseUnmarshaller unmarshaller =
                new ShowTableStateResponseUnmarshaller(result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(TableState.Normal, result.getTableState());
    }

    @Test(expected = BceClientException.class)
    public void testUnmarshallerInvalidJson() throws Exception {
        String resp = "{invalid json}";

        ShowTableStateResponse result = new ShowTableStateResponse();
        ShowTableStateResponseUnmarshaller unmarshaller = new ShowTableStateResponseUnmarshaller(result);
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