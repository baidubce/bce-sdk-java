/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dcc.model;

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
