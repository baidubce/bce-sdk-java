/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.enums;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-08 11:57
 * @Version v1.0
 * <p>
 * The enum of disk type.
 */
public enum DiskTypeEnum {

    NVME("csi-lvm-ssd", "ssd", "1GB"),

    SATA("csi-lvm-hdd", "hdd", "1GB"),

    CDS_HDD("csi-cds-hdd", "cds_hdd", "1GB"),

    CDS_SSD("csi-cds-ssd", "cds_ssd", "1GB"),

    HDD_PASSTHROUGH("local-storage-hdd", "hdd", "1GB"),

    SSD_PASSTHROUGH("local-storage-ssd", "ssd", "1GB"),

    VK_CDS_SSD("vk-csi-cds-ssd", "cds_ssd", "1GB"),

    VK_CDS_HDD("vk-csi-cds-hdd", "cds_hdd", "1GB");

    private String storageClassName;

    private String flavorName;

    private String flavorValue;

    DiskTypeEnum(String storageClassName, String flavorName, String flavorValue) {
        this.storageClassName = storageClassName;
        this.flavorName = flavorName;
        this.flavorValue = flavorValue;
    }
    
    public String getStorageClassName() {
        return storageClassName;
    }

    public String getFlavorName() {
        return this.flavorName;
    }

    public String getFlavorValue() {
        return this.flavorValue;
    }
}