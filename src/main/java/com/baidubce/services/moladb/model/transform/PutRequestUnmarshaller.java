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

import java.util.Map;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.AttributeValue;
import com.baidubce.services.moladb.model.PutRequest;
import com.fasterxml.jackson.databind.JsonNode;

public class PutRequestUnmarshaller implements
        Unmarshaller<PutRequest, JsonNode> {

    @Override
    public PutRequest unmarshall(JsonNode jsonObj) throws Exception {
        ItemUnmarshaller unmarshaller = new ItemUnmarshaller();
        Map<String, AttributeValue> item = unmarshaller.unmarshall(jsonObj.get(MolaDbConstants.JSON_ITEM));
        PutRequest putReq = new PutRequest();
        putReq.withItem(item);
        return putReq;
    }
}