/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of GetClusterRequest.
 * <p>
 * The response contains the properties of the target cluster, such as:
 * id, imageType, imageVersion, logUri, name, autoTerminate, status, applications.
 */
public class GetClusterResponse extends AbstractBceResponse {
    private String id;
    private String imageType;
    private String imageVersion;
    private String logUri;
    private String name;
    private boolean autoTerminate;
    private ClusterStatus status;
    private String orderId;
    private List<Application> applications;
    private boolean serviceHaEnabled;
    private boolean safeModeEnabled;
    private String payType;
    private String availabilityZone;
    private String vpcId;
    private String subnetId;
    private String securityGroupId;
    private boolean enableAutoScale;
    private List<InstanceGroupSummary> instanceGroups;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<InstanceGroupSummary> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupSummary> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public boolean isEnableAutoScale() {
        return enableAutoScale;
    }

    public void setEnableAutoScale(boolean enableAutoScale) {
        this.enableAutoScale = enableAutoScale;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageVersion() {
        return imageVersion;
    }

    public void setImageVersion(String imageVersion) {
        this.imageVersion = imageVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
    }

    public boolean getAutoTerminate() {
        return autoTerminate;
    }

    public void setAutoTerminate(boolean autoTerminate) {
        this.autoTerminate = autoTerminate;
    }

    public ClusterStatus getStatus() {
        return status;
    }

    public void setStatus(ClusterStatus status) {
        this.status = status;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public boolean getServiceHaEnabled() {
        return serviceHaEnabled;
    }

    public void setServiceHaEnabled(boolean serviceHaEnabled) {
        this.serviceHaEnabled = serviceHaEnabled;
    }

    public boolean getSafeModeEnabled() {
        return safeModeEnabled;
    }

    public void setSafeModeEnabled(boolean safeModeEnabled) {
        this.safeModeEnabled = safeModeEnabled;
    }
}
