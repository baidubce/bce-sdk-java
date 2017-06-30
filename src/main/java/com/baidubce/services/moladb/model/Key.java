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

/**
 * Represents the Key part for an item. For each Key, it maybe an attribute for hashkey or two attributes for hashkey and rangekey.
 */
public class Key {
    private Map<String, AttributeValue> attributes;
    
    /**
     * Constructs a new Key object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public Key() {
    }

    /**
     * Constructs a new Key object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param attributeName The attribute name to set for a key.
     * @param value The attribute value to set for a key.
     */
    public Key(String attributeName, AttributeValue value) {
        this.withAttribute(attributeName, value);
    }
    
    /**
     * Constructs a new Key object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param hashKeyName The attribute name to set for a hashKey.
     * @param hashKeyValue The attribute value to set for a hashKey.
     * @param rangeKeyName The attribute name to set for a rangeKey.
     * @param rangeKeyValue The attribute value to set for a rangeKey.
     */
    public Key(String hashKeyName, AttributeValue hashKeyValue, 
            String rangeKeyName, AttributeValue rangeKeyValue) {
        this.withAttribute(hashKeyName, hashKeyValue);
        this.withAttribute(rangeKeyName, rangeKeyValue);
    }

    /**
     * Constructs a new Key object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param attributes An attribute map with name and value to set for a key.
     */
    public Key(Map<String, AttributeValue> attributes) {
        this.attributes = attributes;
    }

    /**
     * The method set attribute name and attribute value with input parameters for a key.
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributeName The attribute name to set for a key.
     * @param value The attribute value to set for a key.
     * @return A reference to this object so that method calls can be chained together.
     */
    public Key withAttribute(String attributeName, AttributeValue value) {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, AttributeValue>();
        }
        attributes.put(attributeName, value);
        return this;
    }

    /**
     * Get this object's attribute map with name and value.
     *
     * @return A map of attributes with name and value.
     */
    public Map<String, AttributeValue> getAttributes() {
        return attributes;
    }

    /**
     * Set this object with a given attribute value map.
     *
     * @param attributes An attribute value map to be set for this object.
     */
    public void setAttributes(Map<String, AttributeValue> attributes) {
        this.attributes = attributes;
    }
    
    /**
     * Set this object with a given attribute value map.
     *
     * @param attributes An attribute value map to be set for this object.
     * @return A reference to this object so that method calls can be chained together.
     */
    public Key withAttributes(Map<String, AttributeValue> attributes) {
        this.setAttributes(attributes);
        return this;
    }

    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return this.attributes.toString();
    }

    protected Map<String, Object> toJsonObj() {
        Map<String, Object> obj = new HashMap<String, Object>();
        for (Map.Entry<String, AttributeValue> entry : this.attributes.entrySet()) {
            obj.put(entry.getKey(), entry.getValue().toJsonObj());
        }
        return obj;
    }
}
