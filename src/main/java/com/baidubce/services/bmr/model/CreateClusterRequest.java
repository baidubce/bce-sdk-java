/*
 * Copyright 2014-2018 Baidu, Inc.
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

import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Provides options for creating a BMR cluster.
 * <p>
 * The essential options are imageType, imageVersion, instanceGroups, and the optional ones are
 * clientToken, name, autoTerminate, logUri, applications and steps.
 */
public class CreateClusterRequest extends AbstractBceRequest {
    private String imageType;
    private String imageVersion;
    private String clientToken;
    private boolean autoTerminate = true;
    private String logUri;
    private String name;
    private boolean serviceHaEnabled;
    private boolean safeModeEnabled;
    private List<InstanceGroupConfig> instanceGroups;
    private List<ApplicationConfig> applications;
    private List<StepConfig> steps;
    private String adminPassword;
    private String securityGroup;
    private String vpcId;
    private String subnetId;
    private String availabilityZone;
    private String templateType;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public boolean getAutoTerminate() {
        return autoTerminate;
    }

    public void setAutoTerminate(boolean autoTerminate) {
        this.autoTerminate = autoTerminate;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<InstanceGroupConfig> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupConfig> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public List<ApplicationConfig> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationConfig> applications) {
        this.applications = applications;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<StepConfig> steps) {
        this.steps = steps;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    /**
     * Configure the image type for the cluster.
     *
     * @param imageType The image type for cluster's instances.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withImageType(String imageType) {
        this.setImageType(imageType);
        return this;
    }

    /**
     * Configure image version for the cluster.
     *
     * @param imageVersion The image version for the cluster's instance.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withImageVersion(String imageVersion) {
        this.setImageVersion(imageVersion);
        return this;
    }

    /**
     * Configure auto-terminate property for the cluster. If set the autoTerminate as true, then
     * the cluster will be terminated when all the steps are done. And the autoTerminate is true by default.
     *
     * @param autoTerminate true if the cluster should be auto terminated.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withAutoTerminate(boolean autoTerminate) {
        this.setAutoTerminate(autoTerminate);
        return this;
    }

    /**
     * Configure optional BOS uri for logs of steps. If the uri is not set, then the logs for the cluster and
     * steps are not saved in the BOS.
     *
     * @param logUri The valid BOS uri for the logs.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withLogUri(String logUri) {
        this.setLogUri(logUri);
        return this;
    }

    /**
     * Configure optional name of the cluster.If not set, then the name of the cluster will be
     * "my-cluster" by default.
     *
     * @param name The name for the cluster.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure optional service HA enable of the cluster. If true will create a ha cluster, default is false.
     *
     * @param serviceHaEnabled true if the serivce ha enable, default is false.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withServiceHaEnabled(boolean serviceHaEnabled) {
        this.setServiceHaEnabled(serviceHaEnabled);
        return this;
    }

    /**
     * Configure optional safe mode enable of the cluster. If true will create a cluster running as safe mode,
     * default is false.
     *
     * @param safeModeEnabled true if the safe mode enable, default is false.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withSafeModeEnabled(boolean safeModeEnabled) {
        this.setSafeModeEnabled(safeModeEnabled);
        return this;
    }

    /**
     * Configure the instance group for the cluster.
     *
     * @param instanceGroup An InstanceGroupConfig instance.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withInstanceGroup(InstanceGroupConfig instanceGroup) {
        if (this.instanceGroups == null) {
            this.instanceGroups = new ArrayList<InstanceGroupConfig>();
        }
        this.instanceGroups.add(instanceGroup);
        return this;
    }

    /**
     * Configure optional application for the cluster. BMR provides applications such as Hive、Pig、HBase for the cluster.
     *
     * @param application An ApplicationConfig instance.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withApplication(ApplicationConfig application) {
        if (this.applications == null) {
            this.applications = new ArrayList<ApplicationConfig>();
        }
        this.applications.add(application);
        return this;
    }

    /**
     * Configure optional step for the cluster. The step will be scheduled and executed after the cluster is ACTIVE.
     * And the step also can be added to the cluster by sending AddStepsRequest.
     *
     * @param step a StepConfig instance to be added.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withStep(StepConfig step) {
        if (this.steps == null) {
            this.steps = new ArrayList<StepConfig>();
        }
        this.steps.add(step);
        return this;
    }

    /**
     * Configure the steps to be added. This method will replace the CreateClusterRequest instance's
     * steps by the @param steps totally, thus it should be invoked ahead of withStep method if both
     * of them are used for the same CreateClusterRequest instance.
     *
     * @param steps a List of StepConfig instances to be added.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withSteps(List<StepConfig> steps) {
        this.setSteps(steps);
        return this;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure request adminPassword for the request.
     *
     * @param adminPassword an password for cluster for example bmrtest@123
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withAdminPassword(String adminPassword) {
        this.setAdminPassword(adminPassword);
        return this;
    }

    /**
     * Configure request vpc network name for the request.
     *
     * @param vpcId vpcId  for create cluster
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withVpcId(String vpcId) {
        this.setVpcId(vpcId);
        return this;
    }

    /**
     * Configure request subnet name for the request.
     *
     * @param subnetId subnetId for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withSubnetId(String subnetId) {
        this.setSubnetId(subnetId);
        return this;
    }

    /**
     * Configure request availabilityZone of creating cluster.
     *
     * @param availabilityZone cluster availabilityZone
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withAvailabilityZone(String availabilityZone) {
        this.setAvailabilityZone(availabilityZone);
        return this;
    }

    /**
     * Configure request security group for the request.
     *
     * @param securityGroup securityGroup for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withSecurityGroup(String securityGroup) {
        this.setSecurityGroup(securityGroup);
        return this;
    }

    /**
     * Configure request templateType for the request.
     *
     * @param templateType templateType for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateClusterRequest withTemplateType(String templateType) {
        this.setTemplateType(templateType);
        return this;
    }
}
