/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.ses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model object of domain attribute. It is contained by @link{DomainDetailModel}.
 */
public class DomainAttrModel {
    /**
     * The URL of domain.
     */
    private String domain;
    /**
     * The status.
     */
    private Integer status;
    /**
     * The DNS record name.
     */
    @JsonProperty("dns_record_name")
    private String dnsRecordName;
    /**
     * The DNS record value.
     */
    @JsonProperty("dns_record_value")
    private String dnsRecordValue;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDnsRecordName() {
        return dnsRecordName;
    }

    public void setDnsRecordName(String dnsRecordName) {
        this.dnsRecordName = dnsRecordName;
    }

    public String getDnsRecordValue() {
        return dnsRecordValue;
    }

    public void setDnsRecordValue(String dnsRecordValue) {
        this.dnsRecordValue = dnsRecordValue;
    }

    @Override
    public String toString() {
        return "DomainAttrModel [domain=" + domain + ", status=" + status + ", dnsRecordName=" + dnsRecordName
                + ", dnsRecordValue=" + dnsRecordValue + "]";
    }

}
