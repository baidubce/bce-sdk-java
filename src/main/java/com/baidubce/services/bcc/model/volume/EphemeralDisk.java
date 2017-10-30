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
package com.baidubce.services.bcc.model.volume;

/**
 * ephemeral disk info.
 */
public class EphemeralDisk {
    /**
     * The storage type of volume, see more detail in
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     */
    private String storageType;

    /**
     * capacity of volume
     */
    private int sizeInGB;

    /**
     * free capacity of volume
     */
    private int freeSizeInGB;

    public EphemeralDisk withStorageType(String storageType) {
        this.setStorageType(storageType);
        return this;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public EphemeralDisk withSizeInGB(int sizeInGB) {
        this.setSizeInGB(sizeInGB);
        return this;
    }

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
}
