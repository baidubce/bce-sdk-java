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

/**
 * @author yixing
 *
 * Cache TTL policy
 */
public class CacheTTL {
    /**
     * suffix"表示文件名后缀，"path"表示url中的目录，"origin"表示源站规则，此规则只有一条，只表示出权重即可
     * value为"-", ttl为 0，"code"表示异常码缓存，如可以配置404缓存100s ，“exactPath”表示路径完全匹配
     * 必选
     */
    private String type;

    /**
     * type所指定类型的配置规则
     * 必选
     */
    private String value;

    /**
     * 缓存时间，单位为秒
     * 必选
     */
    private Integer ttl;

    /**
     * 权重，0-100的整数，权重越高优先级越高，默认为0，优先级在为code类型下是没有作用的，可以忽略
     * 可选
     */
    private Integer weight;

    /**
     * 缓存是否遵循源站，默认true。overrideOrigin=true表示不遵循源站，按照该条配置规则缓存
     * 可选
     */
    private Boolean overrideOrigin;
    
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

    public Boolean getOverrideOrigin() {
        return overrideOrigin;
    }

    public void setOverrideOrigin(Boolean overrideOrigin) {
        this.overrideOrigin = overrideOrigin;
    }

    public CacheTTL withOverrideOrigin(Boolean overrideOrigin) {
        setOverrideOrigin(overrideOrigin);
        return this;
    }
}
