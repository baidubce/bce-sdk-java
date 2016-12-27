/*
 * Copyright 2014 Baidu, Inc.
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

import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baidubce.BceClientException;
import com.baidubce.services.moladb.model.AttributeValue;
import com.fasterxml.jackson.databind.JsonNode;

public class AttributeValueUnmarshaller implements
        Unmarshaller<AttributeValue, JsonNode> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public AttributeValue unmarshall(JsonNode jsonObj) throws Exception {

        if (jsonObj.isNull()) {
            logger.error("Input json obj is null");
            throw new BceClientException("Input json obj is null");
        }
        if (!jsonObj.isObject() || 0 == jsonObj.size()) {
            logger.error("Input json node:" + jsonObj.toString()
                         + " is not a object or size is 0");
            throw new BceClientException("Illegal json:" + jsonObj.toString()
                                         + " for AttributeValue");
        }

        AttributeValue value = new AttributeValue();
        Iterator<String> fieldNames = jsonObj.fieldNames();
        String type = fieldNames.next();
        String val = jsonObj.get(type).asText();
        value.setValue(type, val);
        return value;
    }
}
