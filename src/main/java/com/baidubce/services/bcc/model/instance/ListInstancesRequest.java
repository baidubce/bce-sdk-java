/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
import com.baidubce.model.ListRequest;

/**
 * request model to query instance list
 */
public class ListInstancesRequest extends ListRequest {
    /**
     * The identified internal ip of instance.
     */
    private String internalIp;

    /**
     * specified id of dedicated host which instance be placed
     */
    private String dedicatedHostId;

    /**
     * the name of available zone
     */
    private String zoneName;

    /**
     * The id of the keypair
     */
    private String keypairId;

    /**
     * whether the instance is auto renew or not
     */
    private boolean autoRenew;

    /**
     * The id of ehcCluster.
     */
    private String ehcClusterId;

    /**
     * The fuzzy-matched instance name.
     */
    private String fuzzyInstanceName;

    /**
     * The id of instance.
     */
    private String instanceIds;

    /**
     * The id of volume.
     */
    private String volumeIds;

    /**
     * The name of instance.
     */
    private String instanceNames;

    /**
     * The id of deploySet.
     */
    private String deploySetIds;

    /**
     * The id of security group.
     */
    private String securityGroupIds;

    /**
     * The paymentTiming.
     */
    private String paymentTiming;

    /**
     * The status of instance.
     */
    private String status;

    /**
     * The tags of instance.
     */
    private String tags;

    /**
     * The id of vpc.
     */
    private String vpcId;

    /**
     * The private ip of instance.
     */
    private String privateIps;

    /**
     * The showRdmaTopo of instance.
     */
    private Boolean showRdmaTopo;

    public void setShowRdmaTopo(Boolean showRdmaTopo) {
        this.showRdmaTopo = showRdmaTopo;
    }

    public Boolean getShowRdmaTopo() {
        return showRdmaTopo;
    }

    public String getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(String instanceIds) {
        this.instanceIds = instanceIds;
    }

    public String getVolumeIds() {
        return volumeIds;
    }

    public void setVolumeIds(String volumeIds) {
        this.volumeIds = volumeIds;
    }

    public String getInstanceNames() {
        return instanceNames;
    }

    public void setInstanceNames(String instanceNames) {
        this.instanceNames = instanceNames;
    }

    public String getDeploySetIds() {
        return deploySetIds;
    }

    public void setDeploySetIds(String deploySetIds) {
        this.deploySetIds = deploySetIds;
    }

    public String getSecurityGroupIds() {
        return securityGroupIds;
    }

    public void setSecurityGroupIds(String securityGroupIds) {
        this.securityGroupIds = securityGroupIds;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getPrivateIps() {
        return privateIps;
    }

    public void setPrivateIps(String privateIps) {
        this.privateIps = privateIps;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getDedicatedHostId() {
        return dedicatedHostId;
    }

    public void setDedicatedHostId(String dedicatedHostId) {
        this.dedicatedHostId = dedicatedHostId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public ListInstancesRequest withDedicatedHostId(String dedicatedHostId) {
        this.dedicatedHostId = dedicatedHostId;
        return this;
    }

    public ListInstancesRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    /**
     * Configure internalIp for the request.
     *
     * @param internalIp The internal ip address for accessing the instance.
     * @return ListInstancesRequest with internalIp.
     */
    public ListInstancesRequest withInternalIp(String internalIp) {
        this.internalIp = internalIp;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListInstancesRequest with specified marker.
     */
    @Override
    public ListInstancesRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListInstancesRequest with specified maxKeys.
     */
    @Override
    public ListInstancesRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    /**
     * Configure the request with specified keypairId.
     * @param keypairId The specified keypair id.
     * @return ListInstancesRequest with specified keypairId.
     */
    public ListInstancesRequest withKeypairId(String keypairId) {
        this.keypairId = keypairId;
        return this;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * Configure the request with specified autoRenew.
     * @param autoRenew Whether the instance is auto renew or not. If <code>true<code/>, it means the instance
     *                  is auto renew.
     * @return ListInstancesRequest with specified autoRenew.
     */
    public ListInstancesRequest withAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
        return this;
    }

    public String getEhcClusterId() {
        return ehcClusterId;
    }

    public void setEhcClusterId(String ehcClusterId) {
        this.ehcClusterId = ehcClusterId;
    }

    public ListInstancesRequest withEhcClusterId(String ehcClusterId) {
        this.ehcClusterId = ehcClusterId;
        return this;
    }

    public String getFuzzyInstanceName() {
        return fuzzyInstanceName;
    }

    public void setFuzzyInstanceName(String fuzzyInstanceName) {
        this.fuzzyInstanceName = fuzzyInstanceName;
    }

    public ListInstancesRequest withFuzzyInstanceName(String fuzzyInstanceName) {
        this.fuzzyInstanceName = fuzzyInstanceName;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListInstancesRequest with credentials.
     */
    @Override
    public ListInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
