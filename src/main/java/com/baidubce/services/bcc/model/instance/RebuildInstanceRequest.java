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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for rebuilding the instance, you adminPass is needed.
 */
public class RebuildInstanceRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The id of image.
     */
    private String imageId;

    /**
     * The admin password to login the instance.
     *
     * The admin password to login the instance.
     * The adminPass will be encrypt in AES-128 algorithm
     * with the substring of the former 16 characters of user SecretKey.
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     * BCE API doc</a>
     */
    private String adminPass;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Configure instanceId for the request.
     *
     * @param instanceId The id of the instance which will be rebuild.
     * @return RebuildInstanceRequest with instanceId.
     */
    public RebuildInstanceRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
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

    /**
     * Configure adminPass for the request.
     *
     * @param adminPass The admin password to login the instance.It must be encrypt in AES-128 algorithm
     *                  with the substring of the former 16 characters of user SecretKey.See more detail on
     *       <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                  BCE API doc</a>
     * @return RebuildInstanceRequest with adminPass.
     */
    public RebuildInstanceRequest withAdminPass(String adminPass) {
        this.adminPass = adminPass;
        return this;
    }

    /**
     * Configure imageId for the request.
     *
     * @param imageId The id of the image which will be used to rebuild the instance.
     * @return RebuildInstanceRequest with imageId.
     */
    public RebuildInstanceRequest withImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return RebuildInstanceRequest with credentials.
     */
    @Override
    public RebuildInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
