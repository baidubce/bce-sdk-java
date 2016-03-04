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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a UpdateItem operation.
 */
public class UpdateItemRequest extends AbstractBceRequest {
    private String tableName = "";
    private Map<String, AttributeValueUpdate> attributeUpdates;
    private Key key;

    /**
     * Constructs a new UpdateItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public UpdateItemRequest() {
    }

    /**
     * Set the name of the table containing the item to update.
     *
     * @param name The name of the table containing the item to update.
     */
    public void setTableName(String name) {
        this.tableName = name;
    }

    /**
     * Get the name of the table containing the item to update.
     *
     * @return The name of the table containing the item to update.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the name of the table containing the item to update.
     *
     * @param name The name of the table containing the item to update.
     * @return A reference to this object so that method calls can be chained together.
     */
    public UpdateItemRequest withTableName(String name) {
        this.setTableName(name);
        return this;
    }

    /**
     * Set the Key of the item to be updated with the given Key.
     *
     * @param key The Key of the item to be updated.
     * @return A reference to this object so that method calls can be chained together.
     */
    public UpdateItemRequest withKey(Key key) {
        this.setKey(key);
        return this;
    }
    
    /**
     * Set the Key of the item to be updated with the given Key.
     *
     * @param key The Key of the item to be updated.
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Get key of the update item request.
     *
     * @return Return the key of the item to be updated.
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        HashMap<String, Object> jsonObj = new HashMap<String, Object>();
        jsonObj.put(MolaDbConstants.JSON_KEY, key.toJsonObj());

        Object attrs = this.attributesToJsonObj(this.attributeUpdates);
        jsonObj.put(MolaDbConstants.JSON_ATTRIBUTE_UPDATES, attrs);
        return JsonUtils.toJsonString(jsonObj);
    }
    
    /**
     * Set new attribute value with corresponding attribute name which will be updated.
     *
     * @param attributes A map of attributes to update in an item, the type is Map<String, AttributeValueUpdate>.
     */
    public void setAttributeUpdates(Map<String, AttributeValueUpdate> attributes) {
        this.attributeUpdates = attributes;
    }

    /**
     * Set new attribute value with corresponding attribute name which will be updated.
     *
     * @param attributes A map of attributes to update in an item, the type is Map<String, AttributeValueUpdate>.
     * @return A reference to this object so that method calls can be chained together.
     */
    public UpdateItemRequest withAttributeUpdates(Map<String, AttributeValueUpdate> attributes) {
        this.setAttributeUpdates(attributes);
        return this;
    }
    
    /**
     * Get new attribute value with corresponding attribute name which will be updated.
     *
     * @return A map of attributes to update in an item, the type is Map<String, AttributeValueUpdate>.
     */
    public Map<String, AttributeValueUpdate> getAttributeUpdates() {
        return this.attributeUpdates;
    }
    
    private Object attributesToJsonObj(
            Map<String, AttributeValueUpdate> attrs) {
        Map<String, Object> updateInFamily = new HashMap<String, Object>();
        Iterator<Entry<String, AttributeValueUpdate>> attrIter = attrs.entrySet().iterator();
        while (attrIter.hasNext()) {
            Map.Entry<String, AttributeValueUpdate> attrEntry = attrIter.next();
            String attr = (String) attrEntry.getKey();
            AttributeValueUpdate attrVal = (AttributeValueUpdate) attrEntry.getValue();
            updateInFamily.put(attr, attrVal.toJsonObj());
        }
        return updateInFamily;
    }

    public UpdateItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
