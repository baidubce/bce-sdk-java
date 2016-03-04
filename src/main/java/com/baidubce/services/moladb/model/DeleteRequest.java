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
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a DeleteItem operation.
 */
public class DeleteRequest implements WriteRequest {
    private Key key;

    /**
     * Constructs a new DeleteRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public DeleteRequest() {
    }

    /**
     * Set the Key object to be deleted for this object.
     * 
     * @param key The Key object to be deleted for this object, the type is in Key type.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public DeleteRequest withKey(Key key) {
        this.setKey(key);
        return this;
    }

    /**
     * Set the Key object to be deleted for this object.  
     * 
     * @param key The Key object to be deleted for this object.
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Get the Key object of an attribute, which waits to be deleted. 
     *   
     * @return The Key object of an attribute, which waits to be deleted.
     */
    public Key getKey() {
        return key;
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
        rootObj.put(MolaDbConstants.JSON_KEY, key.toJsonObj());
        return rootObj;
    }
}
