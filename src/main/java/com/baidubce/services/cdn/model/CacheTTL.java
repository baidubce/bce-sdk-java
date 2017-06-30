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

/**
 * @author yixing
 *
 * Cache TTL policy
 */
public class CacheTTL extends JsonObject {
    private String type;
    private String value;
    private Integer ttl;
    private Integer weight;
    
    /**
     * @return type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @param type
     * @return returns this object
     */
    public CacheTTL withType(String type) {
        setType(type);
        return this;
    }
    
    /**
     * @return value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * @param value
     * @return returns this object
     */
    public CacheTTL withValue(String value) {
        setValue(value);
        return this;
    }
    
    /**
     * @return ttl
     */
    public Integer getTtl() {
        return ttl;
    }
    
    /**
     * @param ttl
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
    
    /**
     * @param ttl
     * @return returns this object
     */
    public CacheTTL withTtl(Integer ttl) {
        setTtl(ttl);
        return this;
    }
    
    /**
     * @return weigh
     */
    public Integer getWeight() {
        return weight;
    }
    
    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    /**
     * @param weight
     * @return returns this object
     */
    public CacheTTL withWeigth(Integer weight) {
        setWeight(weight);
        return this;
    }
}
