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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.ShowTableStateResponse;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;

/**
 * Used to parse ShowTableStateResponse from HttpResponse body
 */
public class ShowTableStateResponseUnmarshaller implements Unmarshaller<ShowTableStateResponse, InputStream> {
    private ShowTableStateResponse result;

    /**
     * Constructs a show table state response unmarshaller with response.
     *
     * @param response The show table state response object used to store unmarshall result.
     */
    public ShowTableStateResponseUnmarshaller(AbstractBceResponse response) {
        result = (ShowTableStateResponse) response;
    }

    /**
     * Unmarshal the show table state response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The show table state response.
     * @throws Exception
     */
    @Override
    public ShowTableStateResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        String state = root.get(TableStorageConstants.JSON_TABLE_STATE).asText();

        result.setTableState(TableState.valueOf(state));

        return result;
    }
}
