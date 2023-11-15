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

package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RequestAuth {

    /**
     * a/b/c 三种鉴权方式
     * 必选
     */
    private String type;

    /**
     * 主鉴权key,输入大小写字母，数字，长度6到32位
     * 必选
     */
    private String key1;

    /**
     * 副鉴权Key，输入大小写字母，数字，长度6到32位
     * 可选
     */
    private String key2;

    /**
     * 鉴权缓存时间，支持B或C方式设置该参数，单位为秒；URL鉴权的过期时间为指定“timestamp+timeout”；默认为1800。UTC时间
     * 可选
     */
    private Number timeout = 1800;

    /**
     * 白名单列表，在该名单中的文件名不需要鉴权
     * 可选
     */
    private List<String> whiteList;

    /**
     * 签名参数名，只对typeC生效
     * 可选
     */
    private String signArg;

    /**
     * 时间戳参数名，只对typeC生效
     * 可选
     */
    private String timeArg;

    /**
     * 时间格式。值为10，16或者yyyyMMDDhhmm，分别表示10进制时间格式，
     * 16进制时间格式以及字符串类的时间格式。其中yyyyMMDDhhmm只有type为B的时候可以为该值
     * 可选
     */
    private String timestampMetric;

    public RequestAuth withTimestampMetric(String timestampMetric) {
        this.timestampMetric = timestampMetric;
        return this;
    }
    
    public RequestAuth withType(String type) {
        this.type = type;
        return this;
    }
    
    public RequestAuth withKey1(String key1) {
        this.key1 = key1;
        return this;
    }
    
    public RequestAuth withKey2(String key2) {
        this.key2 = key2;
        return this;
    }
    
    public RequestAuth withTimeout(Number timeout) {
        this.timeout = timeout;
        return this;
    }
    
    public RequestAuth addWhiteList(String entry) {
        if (whiteList == null) {
            whiteList = new ArrayList<String>();
        }
        whiteList.add(entry);
        return this;
    }
    
    public RequestAuth withSignArg(String signArg) {
        this.signArg = signArg;
        return this;
    }
    
    public RequestAuth withTimeArg(String timeArg) {
        this.timeArg = timeArg;
        return this;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getKey1() {
        return key1;
    }
    
    public void setKey1(String key1) {
        this.key1 = key1;
    }
    
    public String getKey2() {
        return key2;
    }
    
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    
    public Number getTimeout() {
        return timeout;
    }
    
    public void setTimeout(Number timeout) {
        this.timeout = timeout;
    }
    
    public List<String> getWhiteList() {
        return whiteList;
    }
    
    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
    
    public String getSignArg() {
        return signArg;
    }
    
    public void setSignArg(String signArg) {
        this.signArg = signArg;
    }
    
    public String getTimeArg() {
        return timeArg;
    }
    
    public void setTimeArg(String timeArg) {
        this.timeArg = timeArg;
    }

    public String getTimestampMetric() {
        return timestampMetric;
    }

    public void setTimestampMetric(String timestampMetric) {
        this.timestampMetric = timestampMetric;
    }
}
