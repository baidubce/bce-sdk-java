/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.bos.model;

import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.util.Base64Utils;
import com.baidubce.util.JsonUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.UnsupportedEncodingException;

/**
 * The request used to encapsulate the query object
 */
@JsonRootName("selectRequest")
@JsonPropertyOrder({"expression", "expressionType", "inputSerialization", "outputSerialization", "requestProgress"})
public class SelectObjectRequest extends GenericObjectRequest {
    @JsonIgnore
    private String key;

    /**
     * Type of target object，json or csv
     */
    @JsonIgnore
    private String selectType;

    /**
     * SQL statement to be executed，will automatically convert to base64 code when set this
     */
    private String expression;

    /**
     * Syntax type of query statement, only "SQL" is supported
     */
    private ExpressionType expressionType = ExpressionType.SQL;

    /**
     * Input flow node, whose child node describes the object format information of query
     */
    private InputSerialization inputSerialization;

    /**
     * Output flow node, whose child node describes the format information returned by query results
     */
    private OutputSerialization outputSerialization;

    /**
     * Select progress information node, whose child nodes describe the execution progress of select operation,
     * and return to the user regularly in 3S
     */
    private RequestProgress requestProgress = new RequestProgress();

    public SelectObjectRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType.toLowerCase();
    }

    public String getSelectType() {
        return selectType;
    }

    public SelectObjectRequest withSelectType(String selectType) {
        this.setSelectType(selectType);
        return this;
    }

    @Override
    public SelectObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SelectObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public enum ExpressionType {
        SQL,
    }

    @Override
    public GenericObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    public void setExpression(String expression) {
        this.expression = Base64Utils.encode(expression);
    }

    public String getExpression() {
        return this.expression;
    }

    public SelectObjectRequest withExpression(String expression) {
        this.setExpression(expression);
        return this;
    }

    public void setInputSerialization(InputSerialization inputSerialization) {
        this.inputSerialization = inputSerialization;
    }

    public InputSerialization getInputSerialization() {
        return inputSerialization;
    }

    public SelectObjectRequest withInputSerialization(InputSerialization inputSerialization) {
        this.setInputSerialization(inputSerialization);
        return this;
    }

    public OutputSerialization getOutputSerialization() {
        return outputSerialization;
    }

    public void setOutputSerialization(OutputSerialization outputSerialization) {
        this.outputSerialization = outputSerialization;
    }

    public SelectObjectRequest withOutputSerialization(OutputSerialization outputSerialization) {
        this.setOutputSerialization(outputSerialization);
        return this;
    }

    public void setRequestProgress(boolean enabled) {
        this.requestProgress.setEnabled(enabled);
    }

    public RequestProgress getRequestProgress() {
        return this.requestProgress;
    }

    public SelectObjectRequest withRequestProgress(boolean enabled) {
        this.setRequestProgress(enabled);
        return this;
    }

    public void setExpressionType(ExpressionType expressionType) {
        this.expressionType = expressionType;
    }

    public ExpressionType getExpressionType() {
        return this.expressionType;
    }

    public SelectObjectRequest withExpressionType(ExpressionType expressionType) {
        this.setExpressionType(expressionType);
        return this;
    }

    public String getOutputValue(String key) {
        return outputSerialization.getValue(key);
    }

    /**
     * Build the request body according to the request parameters
     * @return json body
     */
    public byte[] buildBody() {
        byte[] requestBody = null;

        if (this.selectType.equals(Constants.SELECT_TYPE_CSV)) {
            inputSerialization.setCsvParams(inputSerialization.getParams());
            outputSerialization.setCsvParams(outputSerialization.getParams());
        }
        else if (this.selectType.equals(Constants.SELECT_TYPE_JSON)) {
            inputSerialization.setJsonParams(inputSerialization.getParams());
            outputSerialization.setJsonParams(outputSerialization.getParams());
        } else {
            throw new IllegalArgumentException("selectType should be csv or json");
        }

        String writer = JsonUtils.toJsonStringWithRootName(this);
        try {
            requestBody = writer.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        return requestBody;
    }

    public class RequestProgress {
        private boolean enabled = false;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}