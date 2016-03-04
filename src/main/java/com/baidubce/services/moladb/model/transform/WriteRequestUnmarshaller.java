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

import com.baidubce.BceClientException;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.DeleteRequest;
import com.baidubce.services.moladb.model.PutRequest;
import com.baidubce.services.moladb.model.WriteRequest;
import com.fasterxml.jackson.databind.JsonNode;

public class WriteRequestUnmarshaller implements
        Unmarshaller<WriteRequest, JsonNode> {

    @Override
    public WriteRequest unmarshall(JsonNode jsonObj) throws Exception {
        WriteRequest req = null;
        JsonNode putNode = jsonObj.get(MolaDbConstants.JSON_PUT_REQUEST);
        JsonNode delNode = jsonObj.get(MolaDbConstants.JSON_DELETE_REQUEST);
        if ((null == putNode && null == delNode)
                || (null != putNode && null != delNode)) {
            throw new BceClientException("Invalid responseContent json:"
                                         + jsonObj.toString());
        }

        if (null != putNode) {
            PutRequestUnmarshaller unmarshaller = new PutRequestUnmarshaller();
            PutRequest putRequest = unmarshaller.unmarshall(putNode);
            req = putRequest;
        } else {
            DeleteRequestUnmarshaller unmarshaller = new DeleteRequestUnmarshaller();
            DeleteRequest delRequest = unmarshaller.unmarshall(delNode);
            req = delRequest;
        }
        return req;
    }

}
