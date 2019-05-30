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
import com.baidubce.services.tablestorage.model.ListKeyRangesResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Used to parse ListKeyRangesResponse from HttpResponse body
 */
public class ListKeyRangesResponseUnmarshaller implements Unmarshaller<ListKeyRangesResponse, InputStream> {
    private ListKeyRangesResponse result;

    /**
     * Constructs a list tables response unmarshaller with response object.
     *
     * @param response The list tables response object used to store unmarshalled result.
     */
    public ListKeyRangesResponseUnmarshaller(AbstractBceResponse response) {
        result = (ListKeyRangesResponse) response;
    }

    /**
     * Unmarshal the result of response from TableStorage.
     *
     * @param in The input stream of content to be unmarshalled.
     * @return The list tables response object.
     * @throws Exception
     */
    @Override
    public ListKeyRangesResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("The input json object:" + root.toString() + " is not an object.");
        }

        JsonNode keyRangesObj = root.get(TableStorageConstants.JSON_KEY_RANGES);
        List<Pair<String, String>> keyRanges = new ArrayList<Pair<String, String>>();
        if (keyRangesObj != null) {
            Iterator<JsonNode> keyRangeList = keyRangesObj.elements();
            while (keyRangeList.hasNext()) {
                JsonNode table = keyRangeList.next();
                String startKey = table.get(TableStorageConstants.JSON_START_KEY).asText();
                String stopKey = table.get(TableStorageConstants.JSON_END_KEY).asText();

                keyRanges.add(new ImmutablePair<String, String>(startKey, stopKey));
            }
        }
        result.setKeyRanges(keyRanges);
        return result;
    }
}
