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
package com.baidubce.services.bbc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for rebuild bbc instance
 */
public class RebuildBbcInstanceRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The id which was used to build the instance.
     */
    private String imageId;

    /**
     * The admin password to login the instance.
     * <p>
     * The admin password to login the instance.
     * The adminPass will be encrypt in AES-128 algorithm
     * with the substring of the former 16 characters of user SecretKey.
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     * BCE API doc</a>
     */
    private String adminPass;
    /**
     * Reserving the data or not.
     * If the value is true,the raidId and sysRootSize are ineffective
     */
    private Boolean isPreserveData;
    /**
     * The id of flavor raid.
     * This value is ineffective when isPreserveData is true ,and is required when isPreserveData is false.
     */
    private String raidId;
    /**
     * The size of system root
     * This value is ineffective when isPreserveData is true ,and is required when isPreserveData is false.
     */
    private int sysRootSize;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public Boolean getPreserveData() {
        return isPreserveData;
    }

    public void setPreserveData(Boolean preserveData) {
        isPreserveData = preserveData;
    }

    public String getRaidId() {
        return raidId;
    }

    public void setRaidId(String raidId) {
        this.raidId = raidId;
    }

    public int getSysRootSize() {
        return sysRootSize;
    }

    public void setSysRootSize(int sysRootSize) {
        this.sysRootSize = sysRootSize;
    }

    public RebuildBbcInstanceRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public RebuildBbcInstanceRequest withAdminPass(String adminPass) {
        this.adminPass = adminPass;
        return this;
    }

    public RebuildBbcInstanceRequest withImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public RebuildBbcInstanceRequest isPreservedData(Boolean bool) {
        this.isPreserveData = bool;
        return this;
    }
}
