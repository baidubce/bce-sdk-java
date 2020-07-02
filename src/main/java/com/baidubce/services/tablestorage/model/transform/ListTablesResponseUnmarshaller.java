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
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.ListTablesResponse.TableInfo;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Used to parse ListTablesResponse from HttpResponse body
 */
public class ListTablesResponseUnmarshaller implements Unmarshaller<ListTablesResponse, InputStream> {
    private ListTablesResponse result;

    /**
     * Constructs a list tables response unmarshaller with response object.
     *
     * @param response The list tables response object used to store unmarshalled result.
     */
    public ListTablesResponseUnmarshaller(AbstractBceResponse response) {
        result = (ListTablesResponse) response;
    }

    /**
     * Unmarshal the result of response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The list tables response object.
     * @throws Exception
     */
    @Override
    public ListTablesResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        JsonNode tableObj = root.get(TableStorageConstants.JSON_TABLES);
        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        if (tableObj != null) {
            Iterator<JsonNode> tableList = tableObj.elements();
            while (tableList.hasNext()) {
                JsonNode table = tableList.next();
                String name = table.get(TableStorageConstants.JSON_TABLE_NAME).asText();
                String state = table.get(TableStorageConstants.JSON_TABLE_STATE).asText();
                long version = table.get(TableStorageConstants.JSON_TABLE_VERSION).asLong();
                int maxVersions = table.has(TableStorageConstants.JSON_MAX_VERSIONS)
                        ? table.get(TableStorageConstants.JSON_MAX_VERSIONS).asInt() : 1;
                String storageType = table.get(TableStorageConstants.JSON_STORAGE_TYPE).asText();

                TableInfo info = new TableInfo();
                info.setTableName(name);
                info.setTableState(TableState.valueOf(state));
                info.setTableVersion(version);
                info.setMaxVersions(maxVersions);
                info.setStorageType(storageType);

                tableInfos.add(info);
            }
        }
        result.setTables(tableInfos);
        return result;
    }
}
