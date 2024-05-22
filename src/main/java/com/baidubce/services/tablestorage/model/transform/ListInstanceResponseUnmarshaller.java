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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.InstanceInfo;
import com.baidubce.services.tablestorage.model.ListInstanceResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Used to parse ListInstanceResponse from HttpResponse body
 */
public class ListInstanceResponseUnmarshaller implements Unmarshaller<ListInstanceResponse, InputStream> {
    private ListInstanceResponse result;

    /**
     * Constructs a list instance response object used to store unmarshalled result.
     *
     * @param response The list instance response object used to store unmarshalled
     *                 result
     */
    public ListInstanceResponseUnmarshaller(AbstractBceResponse response) {
        this.result = (ListInstanceResponse) response;
    }

    /**
     * Unmarshal the list instance response from TableStorage
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The list table response object.
     * @throws Exception
     */
    @Override
    public ListInstanceResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        JsonNode instanceObj = root.get(TableStorageConstants.JSON_INSTANCES);
        List<InstanceInfo> instanceInfos = new ArrayList<>();

        if (instanceObj != null) {
            Iterator<JsonNode> elements = instanceObj.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                String id = node.get(TableStorageConstants.JSON_INS_ID).asText();
                String name = node.get(TableStorageConstants.JSON_INS_NAME).asText();
                String region = node.get(TableStorageConstants.JSON_INS_REGION).asText();
                String state = node.get(TableStorageConstants.JSON_INS_STATE).asText();
                String createTime = node.get(TableStorageConstants.JSON_INS_CTIME).asText();
                String storageType = node.get(TableStorageConstants.JSON_INS_STORAGE_TYPE).asText();

                InstanceInfo info = new InstanceInfo();
                info.setId(id);
                info.setName(name);
                info.setRegion(region);
                info.setState(state);
                info.setCreateTime(createTime);
                info.setStorageType(storageType);

                instanceInfos.add(info);
            }
        }

        result.setInstances(instanceInfos);
        return result;
    }
}

/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
