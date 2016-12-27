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

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.Key;
import com.baidubce.services.moladb.model.KeysAndAttributes;
import com.fasterxml.jackson.databind.JsonNode;

public class KeysAndAttributesUnmarshaller implements
        Unmarshaller<KeysAndAttributes, JsonNode> {

    @Override
    public KeysAndAttributes unmarshall(JsonNode jsonObj) throws Exception {
        KeysAndAttributes result = new KeysAndAttributes();
        JsonNode attrsNode = jsonObj.get(MolaDbConstants.JSON_ATTRIBUTES_TO_GET);

        JsonNode consistentRead = jsonObj.get(MolaDbConstants.JSON_CONSISTENT_READ);
        if (consistentRead != null) {
            result.withConsistentRead(consistentRead.asBoolean());
        }
        if (attrsNode != null) {
            List<String> attrsToGet = deserializeAttributes(attrsNode);
            result.setAttributesToGet(attrsToGet);;
        }

        JsonNode keyListObj = jsonObj.get(MolaDbConstants.JSON_KEYS);
        result.withKeys(deserializeKeys(keyListObj));
        return result;
    }

    private List<String> deserializeAttributes(JsonNode jsonObj) {
        List<String> attributes = new ArrayList<String>();
        Iterator<JsonNode> elements = jsonObj.elements();
        while (elements.hasNext()) {
            attributes.add(elements.next().asText());
        }
        return attributes;
    }

    private List<Key> deserializeKeys(JsonNode jsonObj) throws Exception {
        List<Key> keyList = new ArrayList<Key>();
        Iterator<JsonNode> keys = jsonObj.elements();
        while (keys.hasNext()) {
            KeyUnmarshaller unmarshaller = new KeyUnmarshaller();
            Key key = unmarshaller.unmarshall(keys.next());
            keyList.add(key);
        }
        return keyList;
    }
}
