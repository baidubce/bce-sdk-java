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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.GetItemResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class GetItemResponseUnmarshaller implements
        Unmarshaller<GetItemResponse, InputStream> {

    private GetItemResponse result = null;

    public GetItemResponseUnmarshaller(AbstractBceResponse response) {
        result = (GetItemResponse) response;
    }

    public GetItemResponse unmarshall(InputStream in) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(in);
        if (streamContents.isEmpty()) {
            return null;
        }
        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        ItemUnmarshaller itemUnmarshaller = new ItemUnmarshaller();
        result.setItem(itemUnmarshaller.unmarshall(root.get(MolaDbConstants.JSON_ITEM)));
        return result;
    }
}
