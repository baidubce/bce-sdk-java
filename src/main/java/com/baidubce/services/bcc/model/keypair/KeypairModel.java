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
package com.baidubce.services.bcc.model.keypair;

import java.util.Date;

/**
 * KeypairModel denotes an keypair been created
 */
public class KeypairModel {

    /**
     * The Number of instances which uses this keypair
     */
    private int instanceCount;

    /**
     * The name of the keypair
     */
    private String name;

    /**
     * The description of the keypair
     */
    private String description;

    /**
     * The create time of the keypair
     */
    private Date createdTime;

    /**
     * Public key of the keypair
     */
    private String publicKey;

    /**
     * Finger print as its name implies
     */
    private String fingerPrint;

    /**
     * Private key of the keypair
     */
    private String privateKey;

    /**
     * The region of the keypair
     */
    private String regionId;

    /**
     * The id of the keypair
     */
    private String keypairId;

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    @Override
    public String toString() {
        return "KeypairModel{name='" + name
                + "', description='" + description
                + "', createdTime='" + createdTime
                + "', publicKey='" + publicKey
                + "', fingerPrint='" + fingerPrint
                + "', privateKey='" + privateKey
                + "', regionId='" + regionId
                + "', keypairId='" + keypairId
                + "', instanceCount=" + instanceCount
                + "}";
    }
}
