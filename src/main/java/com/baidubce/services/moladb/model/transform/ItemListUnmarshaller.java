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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.baidubce.services.moladb.model.AttributeValue;
import com.fasterxml.jackson.databind.JsonNode;

public class ItemListUnmarshaller implements Unmarshaller<List<Map<String, AttributeValue>>, JsonNode> {

    @Override
    public List<Map<String, AttributeValue>> unmarshall(JsonNode listObj) throws Exception {
        List<Map<String, AttributeValue>> itemList = new ArrayList<Map<String, AttributeValue>>();
        Iterator<JsonNode> itemObjs = listObj.elements();
        while (itemObjs.hasNext()) {
            JsonNode itemObj = itemObjs.next();
            ItemUnmarshaller itemUnmarshaller = new ItemUnmarshaller();
            Map<String, AttributeValue> item = itemUnmarshaller.unmarshall(itemObj);
            itemList.add(item);
        }
        return itemList;
    }

}
