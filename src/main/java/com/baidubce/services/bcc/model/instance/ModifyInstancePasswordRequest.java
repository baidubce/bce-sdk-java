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
 * request for changing the instance with a new password.
 */
public class ModifyInstancePasswordRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * An 8-16 characters String which must contains letters,numbers and symbols.
     * The symbols only contains "!@#$%^*()".
     *
     * The new password will be encrypted in AES-128 algorithm
     * with the substring of the former 16 characters of user SecretKey.
     *
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
     * @param instanceId The id of instance.
     * @return ModifyInstancePasswordRequest with instanceId.
     */
    public ModifyInstancePasswordRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
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
     * @param adminPass The new password string.
     *                  An 8-16 characters String which must contains letters,numbers and symbols .
     *                  The symbols only contains "!@#$%^*()".
     *                  The adminPass will be encrypted in AES-128 algorithm
     *                  with the substring of the former 16 characters of user SecretKey.
     *                  See more detail on
     *            <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     *                  BCE API doc</a>
     * @return ModifyInstancePasswordRequest with new adminPass.
     */
    public ModifyInstancePasswordRequest withAdminPass(String adminPass) {
        this.adminPass = adminPass;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyInstancePasswordRequest with credentials.
     */
    @Override
    public ModifyInstancePasswordRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
