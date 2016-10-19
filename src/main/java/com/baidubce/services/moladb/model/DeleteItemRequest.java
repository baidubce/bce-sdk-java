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
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a DeleteItem operation.
 */
public class DeleteItemRequest extends AbstractBceRequest {
    private String tableName = "";
    private Key key;

    /**
     * Constructs a new DeleteItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public DeleteItemRequest() {
    }

    /**
     * Set the  name of the table from which to delete the item.
     *
     * @param tableName The name of the table from which to delete the item.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public DeleteItemRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * The method set the table name to delete items.
     * 
     * @param tableName The name of the table where to delete the items.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        HashMap<String, Object> jsonObj = new HashMap<String, Object>();
        jsonObj.put(MolaDbConstants.JSON_KEY, key.toJsonObj());
        jsonObj.put(MolaDbConstants.JSON_TABLENAME, tableName);
        return JsonUtils.toJsonString(jsonObj);
    }
    
    /**
     * The method set the Key that used to delete item from the table. 
     *
     * @param key The key that used to describe the key field.
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * The method set the Key that used to get item from the table. 
     *
     * @param key The key that used to describe the key field.
     * @return A reference to this object so that method calls can be chained together
     */
    public DeleteItemRequest withKey(Key key) {
        this.setKey(key);
        return this;
    }

    /**
     * The method return the table name of this request.
     *
     * @return Table name of this request.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * The method return the Key that is used to delete the item.
     *
     * @return The Key that is used to locate and delete the item.
     */
    public Key getKey() {
        return key;
    }

    public DeleteItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
