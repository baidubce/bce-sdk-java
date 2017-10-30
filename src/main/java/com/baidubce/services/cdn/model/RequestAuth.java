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

package com.baidubce.services.cdn.model;

import java.util.ArrayList;
import java.util.List;

public class RequestAuth extends JsonObject {
    private String type;
    private String key1;
    private String key2;
    private Number timeout = 1800;
    private List<String> whiteList;
    private String signArg;
    private String timeArg;
    
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
}
