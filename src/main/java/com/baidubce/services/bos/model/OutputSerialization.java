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

import com.baidubce.util.Base64Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * OutputSerialization for selecting object request
 */
@JsonPropertyOrder({"outputHeader", Constants.SELECT_TYPE_CSV, Constants.SELECT_TYPE_JSON})
public class OutputSerialization {
    /**
     * Output CSV header information at the beginning of the returned result.
     * The default is false. The value of fileHeaderInfo field is use
     */
    private boolean outputHeader = false;

    /**
     * the params of csv or json in outputSerialization
     */
    @JsonIgnore
    private Map<String, String> params = new HashMap<String, String>();

    /**
     * when select csv object, csvParams = params; for composing json string
     */
    @JsonProperty(Constants.SELECT_TYPE_CSV)
    private Map<String, String> csvParams;

    /**
     * when select json object, jsonParams = params; for composing json string
     */
    @JsonProperty(Constants.SELECT_TYPE_JSON)
    private Map<String, String> jsonParams;

    public void setOutputHeader(boolean outputHeader) {
        this.outputHeader = outputHeader;
    }

    public boolean getOutputHeader() {
        return outputHeader;
    }

    public OutputSerialization withOutputHeader(boolean outputHeader) {
        this.setOutputHeader(outputHeader);
        return this;
    }

    public OutputSerialization withQuoteFields(String quoteFields) {
        params.put(Constants.QUOTE_FIELDS, quoteFields);
        return this;
    }

    public OutputSerialization withRecordDelimiter(String recordDelimiter) {
        params.put(Constants.RECORD_DELIMITER, Base64Utils.encode(recordDelimiter));
        return this;
    }

    public OutputSerialization withFieldDelimiter(String fieldDelimiter) {
        params.put(Constants.FIELD_DELIMITER, Base64Utils.encode(fieldDelimiter));
        return this;
    }

    public OutputSerialization withQuoteCharacter(String quoteCharacter) {
        params.put(Constants.QUOTE_CHARACTER, Base64Utils.encode(quoteCharacter));
        return this;
    }

    public Map<String, String> getCsvParams() {
        return csvParams;
    }

    public void setCsvParams(Map<String, String> csvParams) {
        this.csvParams = csvParams;
    }

    public Map<String, String> getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(Map<String, String> jsonParams) {
        this.jsonParams = jsonParams;
    }

    public String getValue(String key) {
        return this.params.get(key);
    }

    public Map<String, String> getParams() {
        return params;
    }
}