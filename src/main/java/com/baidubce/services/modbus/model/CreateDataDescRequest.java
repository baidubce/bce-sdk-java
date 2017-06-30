/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.modbus.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Represent the request for create Data description..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDataDescRequest extends AbstractBceRequest {

    private String parserObjectUuid; // 数据长度 必填

    private Integer length; // 数据长度 必填

    private Integer address; // 十进制地址 必填

    private Integer bit; // bit位 默认-1

    private String name; // 显示名称 必填

    private String kind; // 数据类型 必填 INT, BOOL, REAL, INT32, REAL32

    private String unit; // 单位 有默认值

    @JsonProperty("rh")
    private Double rangeHigher; // 量程上限 可以为空

    @JsonProperty("rl")
    private Double rangeLower; // 量程下限 可以为空

    private String formula; // 公式 可以为空

    @JsonProperty("user_properties")
    private HashMap<String, String> userProperties;

    public String getParserObjectUuid() {
        return parserObjectUuid;
    }

    public void setParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
    }

    public CreateDataDescRequest withParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public CreateDataDescRequest withLength(Integer length) {
        this.length = length;
        return this;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public CreateDataDescRequest withAddress(Integer address) {
        this.address = address;
        return this;
    }

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public CreateDataDescRequest withBit(Integer bit) {
        this.bit = bit;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateDataDescRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public CreateDataDescRequest withKind(String kind) {
        this.kind = kind;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public CreateDataDescRequest withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Double getRangeHigher() {
        return rangeHigher;
    }

    public void setRangeHigher(Double rangeHigher) {
        this.rangeHigher = rangeHigher;
    }

    public CreateDataDescRequest withRangeHigher(Double rangeHigher) {
        this.rangeHigher = rangeHigher;
        return this;
    }

    public Double getRangeLower() {
        return rangeLower;
    }

    public void setRangeLower(Double rangeLower) {
        this.rangeLower = rangeLower;
    }

    public CreateDataDescRequest withRangeLower(Double rangeLower) {
        this.rangeLower = rangeLower;
        return this;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public CreateDataDescRequest withFormula(String formula) {
        this.formula = formula;
        return this;
    }

    public HashMap<String, String> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(HashMap<String, String> userProperties) {
        this.userProperties = userProperties;
    }

    public CreateDataDescRequest withUserProperties(HashMap<String, String> userProperties) {
        this.userProperties = userProperties;
        return this;
    }

    @Override
    public CreateDataDescRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
