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

import java.util.List;
import java.util.Map;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a BatchGetItem operation.
 */
public class BatchGetItemResponse extends AbstractBceResponse {
    private Map<String, List<Map<String, AttributeValue>>> responses;
    private Map<String, KeysAndAttributes> unprocessedItems;

    /**
     * Constructs a new BatchGetItemResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public BatchGetItemResponse() {
    }

    /**
     * Get all of the unprocessed items in this BatchGetItem request.
     * 
     * @return All of the unprocessed items in this BatchGetItem request, the type is in Map<String,KeysAndAttributes> type.
     */
    public Map<String, KeysAndAttributes> getUnprocessedItems() {
        return this.unprocessedItems;
    }

    /**
     * Set all of the unprocessed items in this BatchGetItem request.
     * 
     * @param items All of the unprocessed items in this BatchGetItem request, the type is in Map<String,KeysAndAttributes> type.
     */
    public void setUnprocessedItems(Map<String, KeysAndAttributes> items) {
        this.unprocessedItems = items;
    }
    
    /**
     * Return a string representation of the response information.
     *
     * @return A string representation of the response information.
     */
    public String toString() {
        String str = "{";
        if (this.responses != null) {
            str += "\"responses:" + responses.toString() + ", ";
        }
        if (this.unprocessedItems != null) {
            str += "\"unprocessedItems\":" + unprocessedItems.toString() + ", ";
        }
        str += "}";
        return str;
    }

    /**
     * Set the processed items' content for this BatchGetItem request.
     * 
     * @param responses The processed items' content for this BatchGetItem request.
     */
    public void setResponses(Map<String, List<Map<String, AttributeValue>>> responses) {
        this.responses = responses;
    }
    
    /**
     * Get the processed items' content for this BatchGetItem request.
     * 
     * @return The processed items' content for this BatchGetItem request.
     */
    public Map<String, List<Map<String, AttributeValue>>> getResponses() {
        return this.responses;
    }
}
