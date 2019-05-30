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
package com.baidubce.services.peerconn.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for get peer conn.
 */
public class GetPeerConnResponse extends AbstractBceResponse {

    /**
     * The peerConnId of peer conn.
     */
    private String peerConnId;

    /**
     * The role of peer conn.
     */
    private String role;

    /**
     * The status of peer conn.
     */
    private String status;

    /**
     * The bandwidthInMbps of peer conn.
     */
    private int bandwidthInMbps;

    /**
     * The description of peer conn.
     */
    private String description;

    /**
     * The localIfId of peer conn.
     */
    private String localIfId;

    /**
     * The localIfName of peer conn.
     */
    private String localIfName;

    /**
     * The localVpcId of peer conn.
     */
    private String localVpcId;

    /**
     * The localRegion of peer conn.
     */
    private String localRegion;

    /**
     * The peerAccountId of peer conn.
     */
    private String peerAccountId;

    /**
     * The peerVpcId of peer conn.
     */
    private String peerVpcId;

    /**
     * The peerRegion of peer conn.
     */
    private String peerRegion;

    /**
     * The paymentTiming of peer conn.
     */
    private String paymentTiming;

    /**
     * The dnsStatus of peer conn.
     */
    private String dnsStatus;

    /**
     * The createdTime of peer conn.
     */
    private String createdTime;

    /**
     * The expiredTime of peer conn.
     */
    private String expiredTime;

    public String getPeerConnId() {
        return peerConnId;
    }

    public void setPeerConnId(String peerConnId) {
        this.peerConnId = peerConnId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalIfId() {
        return localIfId;
    }

    public void setLocalIfId(String localIfId) {
        this.localIfId = localIfId;
    }

    public String getLocalIfName() {
        return localIfName;
    }

    public void setLocalIfName(String localIfName) {
        this.localIfName = localIfName;
    }

    public String getLocalVpcId() {
        return localVpcId;
    }

    public void setLocalVpcId(String localVpcId) {
        this.localVpcId = localVpcId;
    }

    public String getLocalRegion() {
        return localRegion;
    }

    public void setLocalRegion(String localRegion) {
        this.localRegion = localRegion;
    }

    public String getPeerAccountId() {
        return peerAccountId;
    }

    public void setPeerAccountId(String peerAccountId) {
        this.peerAccountId = peerAccountId;
    }

    public String getPeerVpcId() {
        return peerVpcId;
    }

    public void setPeerVpcId(String peerVpcId) {
        this.peerVpcId = peerVpcId;
    }

    public String getPeerRegion() {
        return peerRegion;
    }

    public void setPeerRegion(String peerRegion) {
        this.peerRegion = peerRegion;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public String getDnsStatus() {
        return dnsStatus;
    }

    public void setDnsStatus(String dnsStatus) {
        this.dnsStatus = dnsStatus;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }
}
