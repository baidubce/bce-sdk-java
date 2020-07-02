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
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;

/**
 * Used to parse ShowTableResponse from HttpResponse body
 */
public class ShowTableResponseUnmarshaller implements Unmarshaller<ShowTableResponse, InputStream> {
    private ShowTableResponse result;

    /**
     * Constructs a show table response unmarshaller with response object.
     *
     * @param response The show table response object used to store unmarshalled result.
     */
    public ShowTableResponseUnmarshaller(AbstractBceResponse response) {
        result = (ShowTableResponse) response;
    }

    /**
     * Unmarshal the show table response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The show table response object.
     * @throws Exception
     */
    @Override
    public ShowTableResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        String instanceName = root.get(TableStorageConstants.JSON_INSTANCE).asText();
        String tableName = root.get(TableStorageConstants.JSON_TABLE_NAME).asText();
        String state = root.get(TableStorageConstants.JSON_TABLE_STATE).asText();
        long version = root.get(TableStorageConstants.JSON_TABLE_VERSION).asLong();
        String createTime = root.get(TableStorageConstants.JSON_CREATE_TIME).asText();
        String compressType = root.get(TableStorageConstants.JSON_COMPRESS_TYPE).asText();
        int ttl = root.get(TableStorageConstants.JSON_TTL).asInt();
        String storageType = root.get(TableStorageConstants.JSON_STORAGE_TYPE).asText();
        int maxVersions = root.has(TableStorageConstants.JSON_MAX_VERSIONS)
                ? root.get(TableStorageConstants.JSON_MAX_VERSIONS).asInt() : 1;

        result.setInstanceName(instanceName);
        result.setTableName(tableName);
        result.setTableState(TableState.valueOf(state));
        result.setTableVersion(version);
        result.setCreateTime(createTime);
        result.setCompressType(CompressType.valueOf(compressType));
        result.setTimeToLive(ttl);
        result.setStorageType(storageType);
        result.setMaxVersions(maxVersions);

        return result;
    }
}
