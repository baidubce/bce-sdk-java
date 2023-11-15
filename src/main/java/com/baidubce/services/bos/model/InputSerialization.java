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
 * InputSerialization for selecting object request
 */
@JsonPropertyOrder({"compressionType",
        Constants.SELECT_TYPE_CSV,
        Constants.SELECT_TYPE_JSON,
        Constants.SELECT_TYPE_PARQUET})
public class InputSerialization {
    /**
     * Specifies whether the object of the query is compressed
     */
    private String compressionType;

    /**
     * the params of csv, json or parquet in inputSerialization
     */
    @JsonIgnore
    private Map<String, String> params = new HashMap<String, String>();

    /**
     * when select csv object,  csvParams = params; for composing json string
     */
    @JsonProperty(Constants.SELECT_TYPE_CSV)
    private Map<String, String> csvParams;

    /**
     * when select json object, jsonParams = params; for composing json string
     */
    @JsonProperty(Constants.SELECT_TYPE_JSON)
    private Map<String, String> jsonParams;

    /**
     * when select parquet object, parquetParams = params; for composing json string
     */
    @JsonProperty(Constants.SELECT_TYPE_PARQUET)
    private Map<String, String> parquetParams;

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }

    public String getCompressionType() {
        return this.compressionType;
    }

    public InputSerialization withCompressionType(String compressionType) {
        this.setCompressionType(compressionType);
        return this;
    }

    public InputSerialization withFileHeaderInfo(String fileHeaderInfo) {
        params.put(Constants.FILE_HEADER_INFO, fileHeaderInfo);
        return this;
    }

    public InputSerialization withRecordDelimiter(String recordDelimiter) {
        params.put(Constants.RECORD_DELIMITER, Base64Utils.encode(recordDelimiter));
        return this;
    }

    public InputSerialization withFieldDelimiter(String fieldDelimiter) {
        params.put(Constants.FIELD_DELIMITER, Base64Utils.encode(fieldDelimiter));
        return this;
    }

    public InputSerialization withQuoteCharacter(String quoteCharacter) {
        params.put(Constants.QUOTE_CHARACTER, Base64Utils.encode(quoteCharacter));
        return this;
    }

    public InputSerialization withCommentCharacter(String commentCharacter) {
        params.put(Constants.COMMENT_CHARACTER, Base64Utils.encode(commentCharacter));
        return this;
    }

    // set json args
    public InputSerialization withJsonType(String type) {
        params.put("type", type);
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

    public Map<String, String> getParquetParams() {
        return parquetParams;
    }

    public void setParquetParams(Map<String, String> parquetParams) {
        this.parquetParams = parquetParams;
    }

    public Map<String, String> getParams() {
        return params;
    }
}