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
package com.baidubce.services.bcc.model;

/**
 * The model which will be used in purchasing new volume.
 */
public class CreateCdsModel {

    /**
     * The size of volume in GB.
     */
    private int cdsSizeInGB;

    /**
     * The storage type of volume, see more detail in
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     */
    private String storageType ;

    /**
     * The id of snapshot.
     */
    private String snapshotId;

    public int getCdsSizeInGB() {
        return cdsSizeInGB;
    }

    public void setCdsSizeInGB(int cdsSizeInGB) {
        this.cdsSizeInGB = cdsSizeInGB;
    }


    /**
     * Configure cdsSizeInGB for the model.
     *
     * @param cdsSizeInGB The size of volume in GB.
     * @return CreateCdsModel with cdsSizeInGB.
     */
    public CreateCdsModel withCdsSizeInGB(int cdsSizeInGB) {
        this.cdsSizeInGB = cdsSizeInGB;
        return this;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * Configure storageType for the model.
     *
     * @param storageType The storage type of volume, see more detail in
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     * @return CreateCdsModel with storageType.
     */
    public CreateCdsModel withStorageType(String storageType) {
        this.storageType = storageType;
        return this;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Configure snapshotId for the model.
     *
     * @param snapshotId The id of snapshot.
     * @return CreateCdsModel with snapshotId.
     */
    public CreateCdsModel withSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    @Override
    public String toString() {
        return "CreateCdsModel{"
                + "cdsSizeInGB=" + cdsSizeInGB
                + ", storageType='" + storageType + '\''
                + ", snapshotId='" + snapshotId + '\''
                + '}';
    }
}
