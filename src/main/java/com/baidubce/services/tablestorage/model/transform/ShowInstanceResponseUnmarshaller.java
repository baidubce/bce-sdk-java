/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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

import java.io.InputStream;

import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.ShowInstanceResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Used to parse ShowInstanceResponse from HttpResponse body
 */
public class ShowInstanceResponseUnmarshaller implements Unmarshaller<ShowInstanceResponse, InputStream> {
    private ShowInstanceResponse result;

    /**
     * Constructs a show instance response object used to store unmarshalled result.
     *
     * @param response The show instance response object used to store unmarshalled
     *                 result
     */
    public ShowInstanceResponseUnmarshaller(AbstractBceResponse response) {
        this.result = (ShowInstanceResponse) response;
    }

    /**
     * Unmarshal the show instance response from TableStorage
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The show table response object.
     * @throws Exception
     */
    @Override
    public ShowInstanceResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);
        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }
        String id = root.get(TableStorageConstants.JSON_INS_ID).asText();
        String name = root.get(TableStorageConstants.JSON_INS_NAME).asText();
        String region = root.get(TableStorageConstants.JSON_INS_REGION).asText();
        String state = root.get(TableStorageConstants.JSON_INS_STATE).asText();
        String createTime = root.get(TableStorageConstants.JSON_INS_CTIME).asText();
        String storageType = root.get(TableStorageConstants.JSON_INS_STORAGE_TYPE).asText();

        result.setId(id);
        result.setName(name);
        result.setRegion(region);
        result.setState(state);
        result.setCreateTime(createTime);
        result.setStorageType(storageType);

        return result;
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
