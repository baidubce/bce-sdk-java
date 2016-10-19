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
package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a BatchGetItem operation.
 */
public class BatchGetItemRequest extends AbstractBceRequest {
    private Map<String, KeysAndAttributes> requestItems;

    /**
     * Constructs a new BatchGetItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public BatchGetItemRequest() {
    }

    /**
     * Set the request items for BatchGetItem operation.
     * 
     * @param requestItems The request items for BatchGetItem operation.
     */
    public void setRequestItems(
            Map<String, KeysAndAttributes> requestItems) {
        this.requestItems = requestItems;
    }
    
    /**
     * Set the request items for BatchGetItem operation.
     * 
     * @param reqItems The request items for BatchGetItem operation.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public BatchGetItemRequest withRequestItems(
            Map<String, KeysAndAttributes> reqItems) {
        this.setRequestItems(reqItems);
        return this;
    }

    /**
     * Get the request Items set in this BatchGetItem operation.
     * 
     * @return Returns the map of attributes with name and key, the type is in Map<String, KeysAndAttributes>.
     */
    public Map<String, KeysAndAttributes> getRequestItems() {
        return this.requestItems;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        Map<String, Object> jsonObj = new HashMap<String, Object>();
        Map<String, Object> reqItems = new HashMap<String, Object>();
        Iterator<Entry<String, KeysAndAttributes>> iter = this.requestItems.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, KeysAndAttributes> entry = iter.next();
            String tableName = (String) entry.getKey();
            KeysAndAttributes val = (KeysAndAttributes) entry.getValue();
            reqItems.put(tableName, val.toJsonObj());
        }
        jsonObj.put(MolaDbConstants.JSON_REQUEST_ITEMS, reqItems);
        return JsonUtils.toJsonString(jsonObj);
    }

    @Override
    public BatchGetItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}