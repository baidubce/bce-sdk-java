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

import java.util.List;
import java.util.Map;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a BatchWriteItem operation.
 */
public class BatchWriteItemResponse extends AbstractBceResponse {
    private Map<String, List<WriteRequest>> unprocessedItems;

    /**
     * Constructs a new BatchWriteItemResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public BatchWriteItemResponse() {
    }

    /**
     * Get all of the unprocessed items in this BatchWriteItem request.
     * 
     * @return All of the unprocessed items in this BatchWriteItem request, the type is in Map.
     * @see WriteRequest
     */
    public Map<String, List<WriteRequest>> getUnprocessedItems() {
        return this.unprocessedItems;
    }

    /**
     * Set all of the unprocessed items in this BatchWriteItem request.
     * 
     * @param items All of the unprocessed items in this BatchWriteItem request, the type is in Map.
     * @see WriteRequest
     */
    public void setUnprocessedItems(Map<String, List<WriteRequest>> items) {
        this.unprocessedItems = items;
    }

    /**
     * Return a string representation of the response information.
     *
     * @return A string representation of the response information.
     */
    public String toString() {
        String str = "{";
        if (this.unprocessedItems != null) {
            str += "UnprocesedItems:" + this.unprocessedItems.toString();
        }
        str += "}";
        return str;
    }
}
