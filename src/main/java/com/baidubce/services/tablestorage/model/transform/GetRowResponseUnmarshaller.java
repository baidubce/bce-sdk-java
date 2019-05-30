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
import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Used to parse BatchGetRowResponse from HttpResponse body
 */
public class GetRowResponseUnmarshaller implements Unmarshaller<GetRowResponse, InputStream> {
    private GetRowResponse result;

    /**
     * Constructs a get row response unmarshaller with response object.
     *
     * @param response The get row response object used to store unmarshalled result.
     */
    public GetRowResponseUnmarshaller(AbstractBceResponse response) {
        result = (GetRowResponse) response;
    }

    /**
     * Unmarshal the result of response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The get row response object.
     * @throws Exception
     */
    @Override
    public GetRowResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }
        JsonNode resultNode = root.get(TableStorageConstants.JSON_RESULT);
        if (resultNode != null) {
            Iterator<JsonNode> resultList = resultNode.elements();
            while (resultList.hasNext()) {
                JsonNode jsonNode = resultList.next();
                TableStorageResultUnmarshaller unmarshaller = new TableStorageResultUnmarshaller();
                result.setResult(unmarshaller.unmarshall(jsonNode));
                break;
            }
        }
        return result;
    }

}
