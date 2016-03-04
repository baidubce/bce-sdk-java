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
import java.util.List;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a GetItem operation.
 */
public class GetItemRequest extends AbstractBceRequest {
    private String tableName = "";
    private Key key = null;
    private List<String> attributesToGet;
    private boolean consistentRead = false;

    /**
     * Constructs a new GetItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     */
    public GetItemRequest() {
    }
    
    /**
     * Constructs a new GetItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param tableName The name of the table to that to be get item from.
     */
    public GetItemRequest(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        Map<String, Object> jsonObj = new HashMap<String, Object>();
        if (attributesToGet != null && !attributesToGet.isEmpty()) {
            jsonObj.put(MolaDbConstants.JSON_ATTRIBUTES_TO_GET,
                        attributesToGet);
        }
        if (consistentRead) {
            jsonObj.put(MolaDbConstants.JSON_CONSISTENT_READ,
                        MolaDbConstants.JSON_TRUE);
        }
        if (!jsonObj.isEmpty()) {
            return JsonUtils.toJsonString(jsonObj);
        } else {
            return "";
        }
    }

    /**
     * The method set the Key that used to get item from the table. 
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param key The key that used to describe the key field.
     * @return A reference to this updated object so that method calls can be chained together
     */
    public GetItemRequest withKey(Key key) {
        this.setKey(key);
        return this;
    }
    
    /**
     * The method set the Key that used to get item from the table. 
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param key The key that used to describe the key field.
     */
    public void setKey(Key key) {
        this.key = key;
    }
    
    /**
     * The method return the Key that is used to get the item.
     *
     * @return Key to get the item
     */
    public Key getKey() {
        return key;
    }

    /**
     * The method set this read as consistent read.
     *
     * @param consistent Set consistent read as true or false
     */
    public void setConsistentRead(boolean consistent) {
        this.consistentRead = consistent;
    }
    
    /**
     * The method set this read as consistent read.
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param consistent Set consistent read as true or false
     * @return A reference to this object so that method calls can be chained together
     */
    public GetItemRequest withConsistentRead(boolean consistent) {
        this.setConsistentRead(consistent);
        return this;
    }

    /**
     * The method return this consistent read flag.
     *
     * @return Return this request is a consistent read or not
     */
    public boolean isConsistentRead() {
        return consistentRead;
    }

    /**
     * The method set the table name.
     *
     * @param tableName The name of the table to get the items.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * The method set the table name.
     *
     * @return A reference to this object so that method calls can be chained together
     */
    public GetItemRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * The method return the table name of this request
     *
     * @return Table name of this request
     */
    public String getTableName() {
        return this.tableName;
    }
    
    /**
     * The method specify the attributes that is expected to return from the table. 
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param attributeNames The attribute name list which are expected to be returned from the table.
     * @return A reference to this object so that method calls can be chained together
     */
    public void setAttributesToGet(List<String> attributeNames) {
        this.attributesToGet = attributeNames;
    }

    /**
     * The method specify the attributes that is expected to return from the table. 
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param attributeNames The attribute name list which are expected to be returned from the table.
     * @return A reference to this object so that method calls can be chained together
     */
    public GetItemRequest withAttributesToGet(List<String> attributeNames) {
        this.setAttributesToGet(attributeNames);
        return this;
    }
    
    /**
     * The method return all the attribute names which are expected to return from the table. 
     *
     * @return List of the expected attribute names.
     */
    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public GetItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}