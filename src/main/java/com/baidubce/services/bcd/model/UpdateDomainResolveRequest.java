/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcd.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.google.common.base.Objects;

/**
 * @author yangzhensheng
 * @date 2021/5/26
 *
 * @desc the request object to update a domain resolve record.
 */
public class UpdateDomainResolveRequest extends AbstractBceRequest {

    /**
     * the record domain info
     */
    private String domain;

    /**
     * the record routine. the default value is (DEFAULT) some others like
     * 移动(CMNET), 联通(CNC), 教育（EDU), 百度搜索引擎(SEARCH) and so on.
     */
    private String view;

    /**
     * the record type，such as A, AAAA, CNAME, NS, TXT, MX
     */
    private String rdType;

    /**
     * the record alive time
     */
    private Integer ttl;

    /**
     * the record data
     */
    private String rdata;

    /**
     * the zone such as 'baidu.com'.
     */
    private String zoneName;

    /**
     * the unique info about a domain resolve record.
     */
    private Integer recordId;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getRdType() {
        return rdType;
    }

    public void setRdType(String rdType) {
        this.rdType = rdType;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getRdata() {
        return rdata;
    }

    public void setRdata(String rdata) {
        this.rdata = rdata;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("domain", domain)
                .add("view", view)
                .add("rdType", rdType)
                .add("ttl", ttl)
                .add("rdata", rdata)
                .add("zoneName", zoneName)
                .add("recordId", recordId)
                .toString();
    }
}
