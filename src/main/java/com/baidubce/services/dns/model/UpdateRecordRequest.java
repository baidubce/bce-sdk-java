/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.dns.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRecordRequest extends BaseBceRequest {
    /**
     * 主机记录，例如“www”，记录值和zone的name长度加在一起不能超过255字符。
     */
    private String rr;

    /**
     * 解析记录类型，包含：“A”, “CNAME”, “MX”, “TXT”, “NS”, “AAAA”, “SRV”。
     */
    private String type;

    /**
     * 记录值，例如 IP：“192.168.1.1”，CNAME：“cname.baidu.com”，MX：“mail.baidu.com”。
     */
    private String value;

    /**
     * 解析记录在本地DNS服务器的缓存时间（单位：秒），基础版默认300秒，普惠版默认120秒，企业版默认1秒，取值为正整数。
     */
    private Integer ttl;

    /**
     * 描述，长度不超过255个字符。
     */
    private String description;

    /**
     * MX记录的优先级，取值范围：[0,50]。记录类型为MX记录时，此参数必选。
     */
    private Integer priority;

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getRr() {
        return this.rr;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Integer getTtl() {
        return this.ttl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "UpdateRecordRequest{"
                + "rr=" + rr + "\n"
                + "type=" + type + "\n"
                + "value=" + value + "\n"
                + "ttl=" + ttl + "\n"
                + "description=" + description + "\n"
                + "priority=" + priority + "\n"
                + "}";
    }

}