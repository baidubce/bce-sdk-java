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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * This is a DataDescription
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDescription {

    private String uuid;

    private String parserObjectUuid; // 所属project 不可为空

    private Integer bit = -1; // bit位 默认-1

    private Integer length; // 数据长度 不可为空

    private String address; // 十进制地址 不可为空

    private String name; // 显示名称 不可为空

    private String abbreviation; // 缩写 可以为空

    private String kind; // 数据类型 必填 INT, BOOL, REAL, INT32, REAL32

    private String unit; // 单位 有默认值

    @JsonProperty("rh")
    private Double rangeHigher; // 量程上限 可以为空

    @JsonProperty("rl")
    private Double rangeLower; // 量程下限 可以为空

    private String formula; // 公式 可以为空

    private String state;

    private String status;

    private String createTime;

    private String updateTime;

    @JsonProperty("user_properties")
    private HashMap<String, String> userProperties;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParserObjectUuid() {
        return parserObjectUuid;
    }

    public void setParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
    }

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getRangeHigher() {
        return rangeHigher;
    }

    public void setRangeHigher(Double rangeHigher) {
        this.rangeHigher = rangeHigher;
    }

    public Double getRangeLower() {
        return rangeLower;
    }

    public void setRangeLower(Double rangeLower) {
        this.rangeLower = rangeLower;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public HashMap<String, String> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(HashMap<String, String> userProperties) {
        this.userProperties = userProperties;
    }

}
