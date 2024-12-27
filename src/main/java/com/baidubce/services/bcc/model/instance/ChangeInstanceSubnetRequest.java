/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
import java.util.List;

/**
 * The request for changing the instance subnet.
 */
public class ChangeInstanceSubnetRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    private String instanceId;

    /**
     * The id of subnet after changed.
     */
    private String subnetId;

    /**
     * Whether to reboot automatically or not. If <code>true</code>, it means reboot automatically.
     */
    private boolean reboot;

    private List<String> securityGroupIds;

    public List<String> getSecurityGroupIds() {
        return securityGroupIds;
    }

    public void setSecurityGroupIds(List<String> securityGroupIds) {
        this.securityGroupIds = securityGroupIds;
    }

    public List<String> getEnterpriseSecurityGroupIds() {
        return enterpriseSecurityGroupIds;
    }

    public void setEnterpriseSecurityGroupIds(List<String> enterpriseSecurityGroupIds) {
        this.enterpriseSecurityGroupIds = enterpriseSecurityGroupIds;
    }

    private List<String> enterpriseSecurityGroupIds;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public ChangeInstanceSubnetRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public ChangeInstanceSubnetRequest withSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    public ChangeInstanceSubnetRequest withReboot(boolean reboot) {
        this.reboot = reboot;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ChangeInstanceSubnetRequest with credentials.
     */
    public ChangeInstanceSubnetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ChangeInstanceSubnetRequest withEnterpriseSecurityGroupIds(List<String> enterpriseSecurityGroupIds) {
        this.enterpriseSecurityGroupIds = enterpriseSecurityGroupIds;
        return this;
    }

    public ChangeInstanceSubnetRequest withSecurityGroupIds(List<String> securityGroupIds) {
        this.securityGroupIds = securityGroupIds;
        return this;
    }
}
