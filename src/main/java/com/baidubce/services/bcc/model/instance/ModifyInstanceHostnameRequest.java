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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * request for changing the instance with a new hostname.
 */
public class ModifyInstanceHostnameRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The optional parameter to desc the hostname of instance that will be created.
     */
    private String hostname;

    /**
     * Indicates whether automatic reboot
     */
    private boolean isReboot;


    /**
     * Indicates whether open hostname domain
     */
    @JsonProperty("isOpenHostnameDomain")
    private boolean isOpenHostnameDomain;

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
    public ModifyInstanceHostnameRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public ModifyInstanceHostnameRequest withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public boolean isReboot() {
        return isReboot;
    }

    public void setReboot(boolean reboot) {
        isReboot = reboot;
    }

    public ModifyInstanceHostnameRequest withIsReboot(boolean isReboot) {
        this.isReboot = isReboot;
        return this;
    }

    @JsonIgnore
    public boolean isOpenHostnameDomain() {
        return isOpenHostnameDomain;
    }

    public void setOpenHostnameDomain(boolean isOpenHostnameDomain) {
        this.isOpenHostnameDomain = isOpenHostnameDomain;
    }

    public ModifyInstanceHostnameRequest withIsOpenHostnameDomain(boolean isOpenHostnameDomain) {
        this.isOpenHostnameDomain = isOpenHostnameDomain;
        return this;
    }
    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyInstancePasswordRequest with credentials.
     */
    @Override
    public ModifyInstanceHostnameRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
