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
import java.util.List;
import java.util.Map;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a Query operation.
 */
public class QueryRequest extends AbstractBceRequest {

    private boolean consistentRead = false;
    private String tableName;
    private int limit = 0;
    private List<String> attributesToGet;
    private Key exclusiveStartKey;
    private String keyConditionExpression;
    private Map<String, AttributeValue> expressionAttributeValues;
    private String order = MolaDbConstants.JSON_ASC;
    
    /**
     * Constructs a new QueryRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public QueryRequest() {
    }

    /**
     * Constructs a new QueryRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param tableName The name of the table to query.
     */
    public QueryRequest(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the query condition expression for this query request.
     *
     * @return The query condition expression for the query request.
     */
    public String getKeyConditionExpression() {
        return this.keyConditionExpression;
    }
    
    /**
     * Set the query condition expression for this query request.
     *
     * @param expression The query condition expression for the query request.
     */
    public void setKeyConditionExpression(String expression) {
        this.keyConditionExpression = expression;
    }
    
    /**
     * Set the query condition expression for this query request.
     *
     * @param expression The query condition expression for the query request.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withKeyConditionExpression(String expression) {
        this.setKeyConditionExpression(expression);
        return this;
    }
    
    /**
     * Get the values for the parameters in the the query condition expression for this query request.
     *
     * @return The values and parameters for the query condition expression.
     */
    public Map<String, AttributeValue> getExpressionAttributeValues() {
        return this.expressionAttributeValues;
    }
    
    /**
     * Set the values for the parameters in the the query condition expression for this query request.
     *
     * @param attributes The values and parameters for the query condition expression.
     */
    public void setExpressionAttributeValues(Map<String, AttributeValue> attributes) {
        this.expressionAttributeValues = attributes;
    }
    
    /**
     * Set the values for the parameters in the the query condition expression for this query request.
     *
     * @param attributes The values and parameters for the query condition expression.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withExpressionAttributeValues(Map<String, AttributeValue> attributes) {
        this.setExpressionAttributeValues(attributes);
        return this;
    }

    /**
     * Set the name of table for this query items.
     *
     * @param tableName The name of table for query items.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Set the name of table for this query items.
     *
     * @param tableName The name of table for query items.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Get the name of table for this query items.
     *
     * @param tableName The name of table for query items.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the type of the consistency of a read operation.The default value is false, representing that it is
     * eventually consistent read. If the value is true, it is a strongly consistent read.
     *                       
     * @param consistent The consistency of a read operation. The default value is false, representing that it is
     * eventually consistent read. If the value is true, it is a strongly consistent read.
     */
    public void setConsistentRead(boolean consistent) {
        this.consistentRead = consistent;
    }

    /**
     * Set the type of the consistency of a read operation.The default value is false, representing that it is
     * eventually consistent read. If the value is true, it is a strongly consistent read.
     *                       
     * @param consistent The consistency of a read operation. The default value is false, representing that it is
     * eventually consistent read. If the value is true, it is a strongly consistent read.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withConsistentRead(boolean consistent) {
        this.setConsistentRead(consistent);
        return this;
    }
    /**
     * Return true if the consistency is a strongly consistent read; else return false.
     *
     * @return The consistency of a read operation.The default value is false, representing that it is
     * eventually consistent read. If the value is true, it is a strongly consistent read.
     */
    public boolean isConsistentRead() {
        return this.consistentRead;
    }
    
    /**
     * Set the order by rangekey for the query items result, in descedning order or in ascending order.
     *
     * @param order The order by rangekey for the query items result.
     */
    public void setOrder(String order) {
        this.order = order;
    }
    
    /**
     * Set the order by rangekey for the query items result, in descedning order or in ascending order.
     *
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withOrder(String order) {
        this.setOrder(order);
        return this;
    }
    
    /**
     * Get the order by rangekey for the query items result, in descedning order or in ascending order.
     *
     * @return The ordre by rangekey for the query items result.
     */
    public String getOrder() {
        return this.order;
    }
    
    /**
     * Set the query items result in descending order by rangekey.
     * 
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest orderByDesc() {
        this.order = MolaDbConstants.JSON_DESC;
        return this;
    }

    /**
     * Set the query items result in ascending order by rangekey.
     * 
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest orderByAsc() {
        this.order = MolaDbConstants.JSON_ASC;
        return this;
    }
    
    /**
     * Set the maximum number of return query items in one time.
     * 
     * @param limit The maximum number of return query items in one time.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Set the maximum number of return query items in one time.
     * 
     * @param limit The maximum number of return query items in one time.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withLimit(int limit) {
        this.setLimit(limit);
        return this;
    }
    
    /**
     * Get the maximum number of return query items in one time.
     * 
     * @return The maximum number of return query items in one time.
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Get the attributes to retrieve from the QueryRequest.
     * 
     * @return The attributes to retrieve from the QueryRequest, the type is List<String>.
     */
    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }
    
    /**
     * Set the names of attributes to retrieve from the result items.
     * 
     * @param attributes The names of attributes to retrieve from the result items.
     */
    public void setAttributesToGet(List<String> attributes) {
        this.attributesToGet = attributes;
    }

    /**
     * Set the names of attributes to retrieve from the result items.
     * 
     * @param attributes  The names of attributes to retrieve from the result items, the type is List<String>.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withAttributesToGet(List<String> attributes) {
        this.setAttributesToGet(attributes);
        return this;
    }
    
    /**
     * Set the primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey
     * in the previous operation.
     * 
     * @param exclusiveStartKey  The primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey in the previous operation.
     */
    public void setExclusiveStartKey(Key exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
    }

    /**
     * Set the primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey
     * in the previous operation.
     * 
     * @param exclusiveStartKey The primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey 
     * in the previous operation.
     * @return A reference to this object so that method calls can be chained together.
     */
    public QueryRequest withExclusiveStartKey(Key exclusiveStartKey) {
        this.setExclusiveStartKey(exclusiveStartKey);
        return this;
    }
    
    /**
     * Get the primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey
     * in the previous operation.
     * 
     * @return The primary key(hashkey or hashkey and rangekey) of the first item that this 
     * operation will evaluate. Use the value that was returned for LastEvaluatedKey in the previous operation.
     */
    public Key getExclusiveStartKey() {
        return this.exclusiveStartKey;
    }
    
    /**
     * Return a string representation of the object.
     * 
     * @return A string representation of the object.
     */
    public String toString() {
        HashMap<String, Object> jsonObj = new HashMap<String, Object>();
        jsonObj.put(MolaDbConstants.JSON_TABLENAME, this.tableName);
        
        if (!this.attributesToGet.isEmpty()) {
            jsonObj.put(MolaDbConstants.JSON_ATTRIBUTES_TO_GET,
                        this.attributesToGet);
        }
        if (null != this.exclusiveStartKey) {
            jsonObj.put(MolaDbConstants.JSON_EXCLUSIVE_START_KEY, this.exclusiveStartKey.toJsonObj());
        }
        if (this.consistentRead) {
            jsonObj.put(MolaDbConstants.JSON_CONSISTENT_READ, MolaDbConstants.JSON_TRUE);
        }

        if (this.limit > 0) {
            jsonObj.put(MolaDbConstants.JSON_LIMIT, Integer.toString(this.limit));
        }
        
        jsonObj.put(MolaDbConstants.JSON_KEY_CONDITION_EXPRESSION, this.keyConditionExpression);
        jsonObj.put(MolaDbConstants.JSON_EXPRESSION_ATTRIBUTEVALUES, 
                    this.attributeValueToJson(this.expressionAttributeValues));
        jsonObj.put(MolaDbConstants.JSON_ORDER, this.order);
        return JsonUtils.toJsonString(jsonObj);
    }

    public QueryRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
    
    private Map<String, Object> attributeValueToJson(Map<String, AttributeValue> attributes) {
        Map<String, Object> obj = new HashMap<String, Object>();
        for (Map.Entry<String, AttributeValue> entry : attributes.entrySet()) {
            obj.put(entry.getKey(), entry.getValue().toJsonObj());
        }
        return obj;
    }
}