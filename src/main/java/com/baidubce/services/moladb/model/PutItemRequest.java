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
package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a PutItem operation.
 */
public class PutItemRequest extends AbstractBceRequest {

    private String tableName;
    private Map<String, AttributeValue> item;

    /**
     * Constructs a new PutItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public PutItemRequest() {
    }

    /**
     * Constructs a new PutItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param name The name of the table to contain the item.
     */
    public PutItemRequest(String name) {
        this.tableName = name;
    }

    /**
     * Get the name of the table to contain the item.
     * <p>
     * <b>Constraints:</b><br>
     * <b>Length: </b>3 - 32<br>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br>
     *
     * @return The name of the table to contain the item.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the name of the table to contain the item.
     * <p>
     * <b>Constraints:</b><br>
     * <b>Length: </b>3 - 32<br>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br>
     *
     * @param tableName The name of the table to contain the item.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Set the name of the table to contain the item.
     * <p>
     * <b>Constraints:</b><br>
     * <b>Length: </b>3 - 32<br>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br>
     *
     * @param tableName The name of the table to contain the item.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public PutItemRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Set the Item object to be put in in table.
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param item An container of attribute name/value pairs. Only the
     * primary key attributes are required; you can optionally provide other
     * attribute name-value pairs for the item. <p>You must provide all of
     * the attributes for the primary key. For example, with a hash type
     * primary key, you only need to provide the hash attribute. For a
     * hash-and-range type primary key, you must provide both the hash
     * attribute and the range attribute. <p>If you specify any attributes
     * that are part of an key, then the data types for those
     * attributes must match those of the schema in the table's attribute
     * definition. <p>
     * @return A reference to this updated object so that method calls can be chained
     * together.
     */
    public PutItemRequest withItem(Map<String, AttributeValue> item) {
        this.setItem(item);
        return this;
    }

    /**
     * Set the Item object to be put in in table.
     *
     * @param item An container of attribute name/value pairs. Only the
     * primary key attributes are required; you can optionally provide other
     * attribute name-value pairs for the item. <p>You must provide all of
     * the attributes for the primary key. For example, with a hash type
     * primary key, you only need to provide the hash attribute. For a
     * hash-and-range type primary key, you must provide both the hash
     * attribute and the range attribute. <p>If you specify any attributes
     * that are part of an key, then the data types for those
     * attributes must match those of the schema in the table's attribute
     * definition. <p>
     */
    public void setItem(Map<String, AttributeValue> item) {
        this.item = item;
    }

    /**
     * Get the Item objects to be put in in table.
     *
     * @return An map container of attribute name/value pairs.
     */
    public Map<String, AttributeValue> getItem() {
        return this.item;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        Map<String, Object> jsonObj = new HashMap<String, Object>();
        Map<String, Object> itemObj = this.marshallerItem(this.item);
        jsonObj.put(MolaDbConstants.JSON_ITEM, itemObj);
        return JsonUtils.toJsonString(jsonObj);
    }

    protected Map<String, Object> marshallerItem(Map<String, AttributeValue> item) {
        Map<String, Object> obj = new HashMap<String, Object>();
        for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
            obj.put(entry.getKey(), entry.getValue().toJsonObj());
        }
        return obj;
    }

    public PutItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}