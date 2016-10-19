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
import java.util.List;
import java.util.Map;
import com.baidubce.services.moladb.model.AttributeValue;
import com.fasterxml.jackson.databind.JsonNode;

public class BatchGetItemResponseContentUnmarshaller implements
        Unmarshaller<Map<String, List<Map<String, AttributeValue>>>, JsonNode> {

    @Override
    public Map<String, List<Map<String, AttributeValue>>> unmarshall(JsonNode rootObj) throws Exception {
        Map<String, List<Map<String, AttributeValue>>> resp = new HashMap<String, List<Map<String, AttributeValue>>>();
        Iterator<String> tables = rootObj.fieldNames();
        while (tables.hasNext()) {
            String tableName = tables.next();
            JsonNode listObj = rootObj.get(tableName);
            ItemListUnmarshaller itemsUnmarshaller = new ItemListUnmarshaller();
            List<Map<String, AttributeValue>> itemList = itemsUnmarshaller.unmarshall(listObj);
            resp.put(tableName, itemList);
        }
        return resp;
    }
}
