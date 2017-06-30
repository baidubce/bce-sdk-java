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

import java.util.Map;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a GetItem operation.
 */
public class GetItemResponse extends AbstractBceResponse {
    private Map<String, AttributeValue> item;

    /**
     * Constructs a new GetItemResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public GetItemResponse() {
    }

    /**
     * The method return the Item that got from table in Moladb.
     * 
     * @return The Item got from the table in Moladb.
     */
    public Map<String, AttributeValue> getItem() {
        return item;
    }

    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return item.toString();
    }

    /**
     * The method set the Item that in GetItemResponse.
     * 
     * @param other The Items got from the table in MolaDB.
     */
    public void setItem(Map<String, AttributeValue> other) {
        item = other;
    }
}
