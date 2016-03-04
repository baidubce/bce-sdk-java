/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.moladb.model.transform;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.BatchWriteItemResponse;
import com.baidubce.services.moladb.model.WriteRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class BatchWriteItemResponseUnmarshaller implements
        Unmarshaller<BatchWriteItemResponse, InputStream> {

    BatchWriteItemResponse result = null;

    public BatchWriteItemResponseUnmarshaller(AbstractBceResponse response) {
        result = (BatchWriteItemResponse) response;
    }

    @Override
    public BatchWriteItemResponse unmarshall(InputStream stream) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(stream);
        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (root.isNull()) {
            throw new BceClientException("Invalid responseContent:" + streamContents);
        }

        BatchWriteUnprocessItemsUnmarshaller unprocessUnmarshaller = new BatchWriteUnprocessItemsUnmarshaller();

        JsonNode unprocessObj = root.get(MolaDbConstants.JSON_UNPROCESSED_ITEMS);
        Map<String, List<WriteRequest>> unprocess = null;
        if (unprocessObj != null) {
            unprocess = unprocessUnmarshaller.unmarshall(unprocessObj);
        }
        result.setUnprocessedItems(unprocess);
        return result;
    }
}
