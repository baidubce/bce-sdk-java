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
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the attributes to be updated in the UpdateItem operation.
 */
public class AttributeValueUpdate {
    public static final String ACTION_PUT = MolaDbConstants.JSON_PUT;
    public static final String ACTION_DELETE = MolaDbConstants.JSON_DELETE;

    private AttributeValue value;
    private String action;

    /**
     * Constructs a new AttributeValueUpdate object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public AttributeValueUpdate() {
    }
    
    /**
     * Constructs a new AttributeValueUpdate object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param value The attribute value to be updated.
     * @param action The specified action with the attribute.
     */
    public AttributeValueUpdate(AttributeValue value, String action) {
        this.value = value;
        this.action = action;
    }

    /**
     * The method set the new value for an attribute waiting for updating.
     * 
     * @param value The new attribute value for an attribute waiting for updating.
     * @see AttributeValue
     */
    public void setValue(AttributeValue value) {
        this.value = value;
    }

    /**
     * The method set the new value for an attribute waiting for updating.
     * 
     * @param value The new attribute value for an attribute waiting for updating.
     * @see AttributeValue
     * @return
     */
    public AttributeValueUpdate withValue(AttributeValue value) {
        this.setValue(value);
        return this;
    }
    
    /**
     * The method get the new value set for the attribute waiting for updating.
     * 
     * @return The new attribute value for an attribute waiting for updating.
     */
    public AttributeValue getValue() {
        return this.value;
    }

    /**
     * Set the update item operation with specified action. Valid values are PUT and DELETE.
     *
     * @param action The specified action to be set. Valid values are PUT and DELETE.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Set the update item operation with specified action. Valid values are PUT and DELETE.
     *
     * @param action The specified action to be set. Valid values are PUT and DELETE.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */
    public AttributeValueUpdate withAction(String action) {
        this.setAction(action);
        return this;
    }
    
    /**
     * Get the update item operation with specified action. Valid values are PUT and DELETE.
     * 
     * @return The specified action to be set. Valid values are PUT and DELETE.
     */
    public String getAction() {
        return this.action;
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
        Map<String, Object> jsonObj = new HashMap<String, Object>();
        jsonObj.put(MolaDbConstants.JSON_ACTION, this.action);
        if (ACTION_PUT == this.action) {
            jsonObj.put(MolaDbConstants.JSON_VALUE, this.value.toJsonObj());
        }
        return jsonObj;
    }
}
