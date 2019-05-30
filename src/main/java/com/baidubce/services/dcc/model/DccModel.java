/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dcc.model;

import com.baidubce.services.bcc.model.TagModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * DccModel contains information of a dedicated host.
 */
public class DccModel {

    /**
     * The short id of the dcc host.
     */
    private String id;

    /**
     * The name of the dcc
     */
    private String name;

    /**
     * flavor name of the dcc
     */
    private String flavorName;

    /**
     * Resource usage information.
     */
    private ResourceUsage resourceUsage;

    /**
     * billing method of the dcc.
     */
    private String paymentTiming;

    /**
     * Create time of the dcc.
     */
    private Date createTime;

    /**
     * Expire time of the dcc.
     */
    private Date expireTime;

    /**
     * Description string of the dcc.
     */
    private String desc;

    /**
     * Zone name of the dcc.
     */
    private String zoneName;

    /**
     * Current status of the dcc.
     */
    private String status;

    /**
     * Tags bound to the dcc.
     */
    private List<TagModel> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public ResourceUsage getResourceUsage() {
        return resourceUsage;
    }

    public void setResourceUsage(ResourceUsage resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "DccModel{id='" + id
                + "', name='" + name
                + "', flavorName='" + flavorName
                + "', resourceUsage='" + resourceUsage
                + "', paymentTiming='" + paymentTiming
                + "', createTime=" + createTime
                + ", expireTime=" + expireTime
                + ", desc='" + desc
                + "' zoneName='" + zoneName
                + "', status='" + status
                + "', tags=" + Arrays.toString(tags.toArray()) + "}";
    }

    /**
     * Resource usage information of dcc host.
     */
    public static class ResourceUsage {

        private int cpuCount;
        private int freeCpuCount;
        private int memoryCapacityInGB;
        private int freeMemoryCapacityInGB;
        private List<EphemeralDisk> ephemeralDisks;

        public int getCpuCount() {
            return cpuCount;
        }

        public void setCpuCount(int cpuCount) {
            this.cpuCount = cpuCount;
        }

        public int getFreeCpuCount() {
            return freeCpuCount;
        }

        public void setFreeCpuCount(int freeCpuCount) {
            this.freeCpuCount = freeCpuCount;
        }

        public int getMemoryCapacityInGB() {
            return memoryCapacityInGB;
        }

        public void setMemoryCapacityInGB(int memoryCapacityInGB) {
            this.memoryCapacityInGB = memoryCapacityInGB;
        }

        public int getFreeMemoryCapacityInGB() {
            return freeMemoryCapacityInGB;
        }

        public void setFreeMemoryCapacityInGB(int freeMemoryCapacityInGB) {
            this.freeMemoryCapacityInGB = freeMemoryCapacityInGB;
        }

        public List<EphemeralDisk> getEphemeralDisks() {
            return ephemeralDisks;
        }

        public void setEphemeralDisks(List<EphemeralDisk> ephemeralDisks) {
            this.ephemeralDisks = ephemeralDisks;
        }

        @Override
        public String toString() {
            return "ResourceUsage{cpuCount=" + cpuCount
                    + ", freeCpuCount=" + freeCpuCount
                    + ", memoryCapacityInGB=" + memoryCapacityInGB
                    + ", freeMemoryCapacityInGB=" + freeMemoryCapacityInGB
                    + ", ephemeralDisks=" + Arrays.toString(ephemeralDisks.toArray()) + "}";
        }
    }

    /**
     * ephemeral disk information.
     */
    public static class EphemeralDisk {
        /**
         * disk size in GB.
         */
        private int sizeInGB;

        /**
         * free disk size in GB.
         */
        private int freeSizeInGB;

        /**
         * storage type of the disk.
         */
        private String storageType;

        public int getSizeInGB() {
            return sizeInGB;
        }

        public void setSizeInGB(int sizeInGB) {
            this.sizeInGB = sizeInGB;
        }

        public int getFreeSizeInGB() {
            return freeSizeInGB;
        }

        public void setFreeSizeInGB(int freeSizeInGB) {
            this.freeSizeInGB = freeSizeInGB;
        }

        public String getStorageType() {
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }

        @Override
        public String toString() {
            return "EphemeralDisk{sizeInGB=" + sizeInGB
                    + ", freeSizeInGB=" + freeSizeInGB
                    + ", storageType='" + storageType + "'}";
        }
    }
}
