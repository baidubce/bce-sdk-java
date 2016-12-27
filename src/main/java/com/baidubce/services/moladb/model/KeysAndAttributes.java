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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents a set of keys and, for each key, the attributes to retrieve from the table.
 */
public class KeysAndAttributes {
    private List<String> attributesToGet;
    private List<Key> keys;
    private boolean consistentRead = false;

    /**
     * Constructs a new KeysAndAttributes object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public KeysAndAttributes() {
    }

    /**
     * Set the list of Keys to get associated items.
     *
     * @param keys The key attributes and values to get items from a table, the type is List<Key>.
     * @return Returns a reference to this object so that method calls can be chained together.
     */
    public KeysAndAttributes withKeys(List<Key> keys) {
        this.setKeys(keys);
        return this;
    }

    /**
     * Set the list of Keys to get associated items.
     *
     * @param keys The key attributes and values to get items from a table, the type is List<Key>.
     * @throws IllegalArgumentException
     *          if the list is empty.
     */
    public void setKeys(List<Key> keys) {
        if (keys.isEmpty()) {
            throw new IllegalArgumentException("Keys to get is empty");
        }
        this.keys = keys;
    }
    
    /**
     * Set the type of the consistency of a read operation.The default value is false, representing that it is
     *                       eventually consistent read. If the value is true, it is a strongly consistent read.
     *
     * @param consistentRead The consistency of a read operation. The default value is false, representing that it is
     *                       eventually consistent read. If the value is true, it is a strongly consistent read.
     */
    public void setConsistentRead(boolean consistentRead) {
        this.consistentRead = consistentRead;
    }

    /**
     * Set the type of the consistency of a read operation.The default value is false, representing that it is
     *                       eventually consistent read. If the value is true, it is a strongly consistent read.
     *
     * @param consistentRead The consistency of a read operation. The default value is false, representing that it is
     *                       eventually consistent read. If the value is true, it is a strongly consistent read.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */
    public KeysAndAttributes withConsistentRead(boolean consistentRead) {
        this.setConsistentRead(consistentRead);;
        return this;
    }
    
    /**
     * Attributes to retrieve from the table.
     *
     * @param attributes The attributes to retrieve from an item.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */
    public KeysAndAttributes withAttributesToGet(List<String> attributes) {
        this.setAttributesToGet(attributes);
        return this;
    }

    /**
     * Attributes to retrieve from the table.
     *
     * @param attributes The attributes to retrieve from an item.
     */
    public void setAttributesToGet(List<String> attributes) {
        this.attributesToGet = attributes;
    }

    protected Map<String, Object> toJsonObj() {
        Map<String, Object> rootObj = new HashMap<String, Object>();
        if (this.attributesToGet != null && !this.attributesToGet.isEmpty()) {
            rootObj.put(MolaDbConstants.JSON_ATTRIBUTES_TO_GET,
                        this.attributesToGet);
        }
        if (this.consistentRead) {
            rootObj.put(MolaDbConstants.JSON_CONSISTENT_READ,
                        MolaDbConstants.JSON_TRUE);
        }
        List<Object> keyList = new ArrayList<Object>();
        for (Iterator<Key> iter = this.keys.iterator(); iter.hasNext();) {
            keyList.add(iter.next().toJsonObj());
        }
        rootObj.put(MolaDbConstants.JSON_KEYS, keyList);
        return rootObj;
    }

    /**
     * Return true if the consistency is a strongly consistent read; else return false.
     *
     * @return The consistency of a read operation.The default value is false, representing that it is
     *         eventually consistent read. If the value is true, it is a strongly consistent read.
     */
    public boolean isConsistentRead() {
        return consistentRead;
    }

    /**
     * Get all of the attributes to retrieve from a table.
     *
     * @return All of the attributes to retrieve from a table, the type is List<String>.
     */
    public List<String> getAttributesToGet() {
        return attributesToGet;
    }

    /**
     * Get the key attributes the define the items and the attributes associated with the items in this object.
     *
     * @return A list of Key with key attributes and theirs attribute values.
     */
    public List<Key> getKeys() {
        return keys;
    }

    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return JsonUtils.toJsonString(this.toJsonObj());
    }
}