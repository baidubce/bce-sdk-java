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
 * Represents the output of a Query operation.
 */
public class QueryResponse extends AbstractBceResponse {
    private Key lastEvaluatedKey;
    private List<Map<String, AttributeValue>> items; 
    
    /**
     * Constructs a new QueryResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public QueryResponse() {
    }
    
    /**
     * Set the LastEvaluatedKey for this query operation, and for next time's request, you can get next page of items
     * from the LastEvaluatedKey.
     * 
     * @param lastKey The LastEvaluatedKey for this query operation, and for next time's request, you can get next page of items
     * from the LastEvaluatedKey.
     */
    public void setLastEvaluatedKey(Key lastKey) {
        this.lastEvaluatedKey = lastKey;
    }
    
    /**
     * Set the list of result items from this query operation.
     * 
     * @param items A list of result items from this query operation.
     */
    public void setItems(List<Map<String, AttributeValue>> items) {
        this.items = items;
    }
    
    /**
     * Get the LastEvaluatedKey for this query operation, and for next time's request, you can get next page of items
     * from the LastEvaluatedKey.
     * 
     * @return The LastEvaluatedKey for this query operation, and for next time's request, you can get next page of items from the LastEvaluatedKey.
     * @see Key
     */
    public Key getLastEvaluatedKey() {
        return this.lastEvaluatedKey;
    }
    
    /**
     * Get the list of result items from this query operation.
     * 
     * @return A list of result items from this query operation.
     */
    public List<Map<String, AttributeValue>> getItems() {
        return this.items;
    }
}
