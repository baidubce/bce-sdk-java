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

import java.util.Map;
import java.util.HashMap;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input to perform a PutItem operation on an item.
 */
public class PutRequest implements WriteRequest {
    private Map<String, AttributeValue> item;

    /**
     * Constructs a new PutRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public PutRequest() {
    }

    /**
     * Get an item from this request.
     *
     * @return A map container of attribute name/value pairs. 
     */
    public Map<String, AttributeValue> getItem() {
        return item;
    }

    /**
     * Set an item information with a put item request.
     *
     * @param item An container of attribute name/value pairs. Only the
     *         primary key attributes are required; you can optionally provide other
     *         attribute name-value pairs for the item. <p>You must provide all of
     *         the attributes for the primary key. For example, with a hash type
     *         primary key, you only need to provide the hash attribute. For a
     *         hash-and-range type primary key, you must provide both the hash
     *         attribute and the range attribute. <p>If you specify any attributes
     *         that are part of an key, then the data types for those
     *         attributes must match those of the schema in the table's attribute
     *         definition. <p>
     */
    public void setItem(Map<String, AttributeValue> item) {
        this.item = item;
    }

    /**
     * Set an item information with a put item request.
     *
     * @param item An container of attribute name/value pairs. Only the
     *         primary key attributes are required; you can optionally provide other
     *         attribute name-value pairs for the item. <p>You must provide all of
     *         the attributes for the primary key. For example, with a hash type
     *         primary key, you only need to provide the hash attribute. For a
     *         hash-and-range type primary key, you must provide both the hash
     *         attribute and the range attribute. <p>If you specify any attributes
     *         that are part of an key, then the data types for those
     *         attributes must match those of the schema in the table's attribute
     *         definition. <p>
     * @return Returns a reference to this object so that method calls can be chained together.
     */
    public PutRequest withItem(Map<String, AttributeValue> item) {
        this.setItem(item);
        return this;
    }
    
    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return JsonUtils.toJsonString(this.toJsonObj());
    }
    
    protected Map<String, Object> toJsonObj() {
        Map<String, Object> rootObj = new HashMap<String, Object>();
        Map<String, Object> itemJson = this.marshaller(this.item);
        rootObj.put(MolaDbConstants.JSON_ITEM, itemJson);
        return rootObj;
    }

    protected Map<String, Object> marshaller(Map<String, AttributeValue> item) {
        Map<String, Object> obj = new HashMap<String, Object>();
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            obj.put(entry.getKey(), entry.getValue().toJsonObj());
        }
        return obj;
    }
}
