/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.localdns.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteRecordRequest extends BaseBceRequest {
    /**
     * 解析记录的ID
     */
    private String recordId;

    /**
     * 主机记录
     */
    private String rr;

    /**
     * 记录值
     */
    private String value;

    /**
     * 解析记录类型，目前支持A, AAAA,CNAME, TXT, MX, PTR, SRV
     */
    private String type;

    /**
     * 生存时间，值为[5,24*3600]，默认为60
     */
    private Integer ttl;

    /**
     * MX记录的优先级，取值范围：[0,100]。记录类型为MX记录时，此参数必选。
     */
    private Integer priority;

    /**
     * 描述
     */
    private String description;

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getRr() {
        return this.rr;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Integer getTtl() {
        return this.ttl;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "DeleteRecordRequest{"
                + "recordId=" + recordId + "\n"
                + "rr=" + rr + "\n"
                + "value=" + value + "\n"
                + "type=" + type + "\n"
                + "ttl=" + ttl + "\n"
                + "priority=" + priority + "\n"
                + "description=" + description + "\n"
                + "}";
    }

}