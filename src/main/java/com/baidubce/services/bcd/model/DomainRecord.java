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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author yangzhensheng
 * @date 2021/6/17
 * @desc the domain resolve record.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainRecord {

    @JsonProperty("recordId")
    private int recordId;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("view")
    private String view;

    /**
     * A、AAAA、CNAME、TXT、NS、MX、SOA、SRV
     */
    @JsonProperty("rdtype")
    private String rdType;

    /**
     * time to live
     */
    @JsonProperty("ttl")
    private int timeToLive;

    /**
     * the raw data could be cname or ip address
     */
    @JsonProperty("rdata")
    private String rawData;

    /**
     * the zone name for this domain record
     */
    @JsonProperty("zoneName")
    private String zone;

    /**
     * RUNNING、STOPPED
     */
    @JsonProperty("status")
    private String status;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

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

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("recordId", recordId)
                .add("domain", domain)
                .add("view", view)
                .add("rdType", rdType)
                .add("timeToLive", timeToLive)
                .add("rawData", rawData)
                .add("zone", zone)
                .add("status", status)
                .toString();
    }
}
