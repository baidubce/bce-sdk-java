/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.as.model;

public class AsEipModel {
    /**
     * Maximum bandwidth in Mbps.
     */
    private int bandwidthInMbps;

    /**
     * Public IP.
     */
    private String address;

    /**
     * The status of EIP.
     */
    private String eipStatus;

    /**
     * Instance ID of Elastic public network IP
     */
    private String eipAllocationId;

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEipStatus() {
        return eipStatus;
    }

    public void setEipStatus(String eipStatus) {
        this.eipStatus = eipStatus;
    }

    public String getEipAllocationId() {
        return eipAllocationId;
    }

    public void setEipAllocationId(String eipAllocationId) {
        this.eipAllocationId = eipAllocationId;
    }

    @Override
    public String toString() {
        return "AsEipModel{" +
                "bandwidthInMbps=" + bandwidthInMbps +
                ", address='" + address + '\'' +
                ", eipStatus='" + eipStatus + '\'' +
                ", eipAllocationId='" + eipAllocationId + '\'' +
                '}';
    }
}
