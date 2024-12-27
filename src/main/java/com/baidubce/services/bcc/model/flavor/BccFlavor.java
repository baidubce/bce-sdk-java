/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.flavor;

public class BccFlavor {

    private int cpuCount;
    private int memoryCapacityInGB;
    private Integer ephemeralDiskInGb;
    private Integer ephemeralDiskCount = 1;
    private String ephemeralDiskType;
    private String gpuCardType;
    private Integer gpuCardCount;
    private String fpgaCardType;
    private Integer fpgaCardCount;
    private String productType;
    private String spec;
    private String specId;

    private String flavorSubType;
    private String cpuModel;
    private String cpuGHz;
    private String networkBandwidth;
    private String networkPackage;

    private Integer netEthQueueCount;

    private Boolean enableJumboFrame;

    private Integer netEthMaxQueueCount;

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getMemoryCapacityInGB() {
        return memoryCapacityInGB;
    }

    public void setMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
    }

    public Integer getEphemeralDiskInGb() {
        return ephemeralDiskInGb;
    }

    public void setEphemeralDiskInGb(Integer ephemeralDiskInGb) {
        this.ephemeralDiskInGb = ephemeralDiskInGb;
    }

    public Integer getEphemeralDiskCount() {
        return ephemeralDiskCount;
    }

    public void setEphemeralDiskCount(Integer ephemeralDiskCount) {
        this.ephemeralDiskCount = ephemeralDiskCount;
    }

    public String getEphemeralDiskType() {
        return ephemeralDiskType;
    }

    public void setEphemeralDiskType(String ephemeralDiskType) {
        this.ephemeralDiskType = ephemeralDiskType;
    }

    public String getGpuCardType() {
        return gpuCardType;
    }

    public void setGpuCardType(String gpuCardType) {
        this.gpuCardType = gpuCardType;
    }

    public Integer getGpuCardCount() {
        return gpuCardCount;
    }

    public void setGpuCardCount(Integer gpuCardCount) {
        this.gpuCardCount = gpuCardCount;
    }

    public String getFpgaCardType() {
        return fpgaCardType;
    }

    public void setFpgaCardType(String fpgaCardType) {
        this.fpgaCardType = fpgaCardType;
    }

    public Integer getFpgaCardCount() {
        return fpgaCardCount;
    }

    public void setFpgaCardCount(Integer fpgaCardCount) {
        this.fpgaCardCount = fpgaCardCount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getCpuGHz() {
        return cpuGHz;
    }

    public void setCpuGHz(String cpuGHz) {
        this.cpuGHz = cpuGHz;
    }

    public String getNetworkBandwidth() {
        return networkBandwidth;
    }

    public void setNetworkBandwidth(String networkBandwidth) {
        this.networkBandwidth = networkBandwidth;
    }

    public String getNetworkPackage() {
        return networkPackage;
    }

    public void setNetworkPackage(String networkPackage) {
        this.networkPackage = networkPackage;
    }

    public Integer getNetEthQueueCount() {
        return netEthQueueCount;
    }

    public void setNetEthQueueCount(Integer netEthQueueCount) {
        this.netEthQueueCount = netEthQueueCount;
    }

    public Integer getNetEthMaxQueueCount() {
        return netEthMaxQueueCount;
    }

    public void setNetEthMaxQueueCount(Integer netEthMaxQueueCount) {
        this.netEthMaxQueueCount = netEthMaxQueueCount;
    }

    public Boolean getEnableJumboFrame() {
        return enableJumboFrame;
    }

    public void setEnableJumboFrame(Boolean enableJumboFrame) {
        this.enableJumboFrame = enableJumboFrame;
    }

    public String getFlavorSubType() {
        return flavorSubType;
    }

    public void setFlavorSubType(String flavorSubType) {
        this.flavorSubType = flavorSubType;
    }

    @Override
    public String toString() {
        return "BccFlavor{" +
                "cpuCount=" + cpuCount +
                ", memoryCapacityInGB=" + memoryCapacityInGB +
                ", ephemeralDiskInGb=" + ephemeralDiskInGb +
                ", ephemeralDiskCount=" + ephemeralDiskCount +
                ", ephemeralDiskType='" + ephemeralDiskType + '\'' +
                ", gpuCardType='" + gpuCardType + '\'' +
                ", gpuCardCount=" + gpuCardCount +
                ", fpgaCardType='" + fpgaCardType + '\'' +
                ", fpgaCardCount=" + fpgaCardCount +
                ", productType='" + productType + '\'' +
                ", spec='" + spec + '\'' +
                ", specId='" + specId + '\'' +
                ", cpuModel='" + cpuModel + '\'' +
                ", cpuGHz='" + cpuGHz + '\'' +
                ", networkBandwidth='" + networkBandwidth + '\'' +
                ", networkPackage='" + networkPackage + '\'' +
                ", netEthQueueCount=" + netEthQueueCount +
                ", netEthMaxQueueCount=" + netEthMaxQueueCount +
                ", flavorSubType=" + flavorSubType +
                '}';
    }
}
