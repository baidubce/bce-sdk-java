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
import com.baidubce.services.tablestorage.model.ScanResponse;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Used to parse ScanResponse from HttpResponse body
 */
public class ScanResponseUnmarshaller implements Unmarshaller<ScanResponse, InputStream> {
    private ScanResponse result;

    /**
     * Constructs a scan response unmarshaller with response object.
     *
     * @param response The show table response object used to store unmarshalled result.
     */
    public ScanResponseUnmarshaller(AbstractBceResponse response) {
        result = (ScanResponse) response;
    }

    /**
     * Unmarshal the scan response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The scan response object.
     * @throws Exception
     */
    @Override
    public ScanResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        String nextStartKey = "";
        if (root.has(TableStorageConstants.JSON_NEXT_START_KEY)) {
            nextStartKey = root.get(TableStorageConstants.JSON_NEXT_START_KEY).asText();
        }
        result.setNextStartKey(URLDecoder.decode(nextStartKey, TableStorageConstants.DEFAULT_ENCODING));
        JsonNode resultsNode = root.get(TableStorageConstants.JSON_RESULT);
        List<TableStorageResult> results = new ArrayList<TableStorageResult>();
        if (resultsNode != null) {
            Iterator<JsonNode> resultList = resultsNode.elements();
            TableStorageResultUnmarshaller unmarshaller = new TableStorageResultUnmarshaller();
            while (resultList.hasNext()) {
                JsonNode resultNode = resultList.next();
                TableStorageResult result = unmarshaller.unmarshall(resultNode);
                results.add(result);
            }
        }
        result.setResults(results);

        return result;
    }

}
