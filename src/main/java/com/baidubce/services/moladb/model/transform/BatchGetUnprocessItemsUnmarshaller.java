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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.baidubce.services.moladb.model.KeysAndAttributes;
import com.fasterxml.jackson.databind.JsonNode;

public class BatchGetUnprocessItemsUnmarshaller implements
        Unmarshaller<Map<String, KeysAndAttributes>, JsonNode> {
    @Override
    public Map<String, KeysAndAttributes> unmarshall(JsonNode jsonObj) throws Exception {
        Map<String, KeysAndAttributes> items = new HashMap<String, KeysAndAttributes>();
        Iterator<String> tables = jsonObj.fieldNames();
        while (tables.hasNext()) {
            String tableName = tables.next();
            JsonNode unprocessed = jsonObj.get(tableName);
            KeysAndAttributesUnmarshaller keysUnmarshaller = new KeysAndAttributesUnmarshaller();
            KeysAndAttributes attrs = keysUnmarshaller.unmarshall(unprocessed);
            items.put(tableName, attrs);
        }
        return items;
    }
}
