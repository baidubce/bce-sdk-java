/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * instanceGroup is the group of instance which is configured identically
 */
public class InstanceGroup {
    private String id;
    private String instanceType;
    private String name;
    private String type;
    private String spec;
    private int cpu;
    private int memory;
    private boolean isSpot;
    private String bidModel;
    private String bidPrice;
    private List<CdsItem> cds = new ArrayList<CdsItem>();
    private String diskType;
    private int localDiskSize;
    private int rootDiskSizeInGB;
    private String rootDiskMediumType;
    private int requestedInstanceCount;
    private int totalInstanceCount;
    private int runningInstanceCount;
    private int maxCount;
    private int minCount;
    private int canExpand;
    private int canShrink;
    private String code;
    private String message;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public boolean isSpot() {
        return isSpot;
    }

    public void setSpot(boolean spot) {
        isSpot = spot;
    }

    public String getBidModel() {
        return bidModel;
    }

    public void setBidModel(String bidModel) {
        this.bidModel = bidModel;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public int getLocalDiskSize() {
        return localDiskSize;
    }

    public void setLocalDiskSize(int localDiskSize) {
        this.localDiskSize = localDiskSize;
    }

    public int getTotalInstanceCount() {
        return totalInstanceCount;
    }

    public void setTotalInstanceCount(int totalInstanceCount) {
        this.totalInstanceCount = totalInstanceCount;
    }

    public int getRunningInstanceCount() {
        return runningInstanceCount;
    }

    public void setRunningInstanceCount(int runningInstanceCount) {
        this.runningInstanceCount = runningInstanceCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getCanExpand() {
        return canExpand;
    }

    public void setCanExpand(int canExpand) {
        this.canExpand = canExpand;
    }

    public int getCanShrink() {
        return canShrink;
    }

    public void setCanShrink(int canShrink) {
        this.canShrink = canShrink;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequestedInstanceCount() {
        return this.requestedInstanceCount;
    }

    public void setRequestedInstanceCount(int requestedInstanceCount) {
        this.requestedInstanceCount = requestedInstanceCount;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRootDiskSizeInGB() {
        return rootDiskSizeInGB;
    }

    public void setRootDiskSizeInGB(int rootDiskSizeInGB) {
        this.rootDiskSizeInGB = rootDiskSizeInGB;
    }

    public String getRootDiskMediumType() {
        return rootDiskMediumType;
    }

    public void setRootDiskMediumType(String rootDiskMediumType) {
        this.rootDiskMediumType = rootDiskMediumType;
    }

    public List<CdsItem> getCds() {
        return cds;
    }

    public void setCds(List<CdsItem> cds) {
        this.cds = cds;
    }
}
