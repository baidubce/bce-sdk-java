package com.baidubce.services.bmr.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateTemplateRequest extends AbstractBceRequest {
    private String name;
    private String payType;
    private List<Application> applications;
    private Boolean autoTerminate = false;
    private String imageType;
    private String imageVersion;
    private List<InstanceGroupConfig> instanceGroups;
    private String logUri;
    private List<StepConfig> steps;
    private String adminPassword;
    private String availabilityZone;
    private String vpcId;
    private String subnetId;
    private String systemSecurityGroup;
    private Boolean serviceHaEnabled = false;
    private Boolean safeModeEnabled = false;
    private String templateType;


    /**
     * Configure the template type for the cluster.
     *
     * @param templateType The template type for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateTemplateRequest withTemplateType(String templateType) {
        this.setTemplateType(templateType);
        return this;
    }

    /**
     * Configure the image type for the cluster.
     *
     * @param imageType The image type for cluster's instances.
     *
     * @return CreateClusterRequest
     */
    public CreateTemplateRequest withImageType(String imageType) {
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
    public CreateTemplateRequest withImageVersion(String imageVersion) {
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
    public CreateTemplateRequest withAutoTerminate(boolean autoTerminate) {
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
    public CreateTemplateRequest withLogUri(String logUri) {
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
    public CreateTemplateRequest withName(String name) {
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
    public CreateTemplateRequest withServiceHaEnabled(boolean serviceHaEnabled) {
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
    public CreateTemplateRequest withSafeModeEnabled(boolean safeModeEnabled) {
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
    public CreateTemplateRequest withInstanceGroup(InstanceGroupConfig instanceGroup) {
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
    public CreateTemplateRequest withApplication(Application application) {
        if (this.applications == null) {
            this.applications = new ArrayList<Application>();
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
    public CreateTemplateRequest withStep(StepConfig step) {
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
    public CreateTemplateRequest withSteps(List<StepConfig> steps) {
        this.setSteps(steps);
        return this;
    }

    /**
     * Configure request adminPassword for the request.
     *
     * @param adminPassword adminPassword for example bmrtest@123
     *
     * @return CreateClusterRequest
     */
    public CreateTemplateRequest withAdminPassword(String adminPassword) {
        this.setAdminPassword(adminPassword);
        return this;
    }

    /**
     * Configure request vpc network name for the request.
     *
     * @param vpcId vpcId for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateTemplateRequest withVpcId(String vpcId) {
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
    public CreateTemplateRequest withSubnetId(String subnetId) {
        this.setSubnetId(subnetId);
        return this;
    }

    /**
     * Configure request availabilityZone of creating cluster.
     *
     * @param availabilityZone availabilityZone for cluster
     *
     * @return CreateClusterRequest
     */
    public CreateTemplateRequest withAvailabilityZone(String availabilityZone) {
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
    public CreateTemplateRequest withSecurityGroup(String securityGroup) {
        this.setSystemSecurityGroup(securityGroup);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Boolean getAutoTerminate() {
        return autoTerminate;
    }

    public void setAutoTerminate(Boolean autoTerminate) {
        this.autoTerminate = autoTerminate;
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

    public List<InstanceGroupConfig> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupConfig> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
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

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
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

    public String getSystemSecurityGroup() {
        return systemSecurityGroup;
    }

    public void setSystemSecurityGroup(String systemSecurityGroup) {
        this.systemSecurityGroup = systemSecurityGroup;
    }

    public Boolean getServiceHaEnabled() {
        return serviceHaEnabled;
    }

    public void setServiceHaEnabled(Boolean serviceHaEnabled) {
        this.serviceHaEnabled = serviceHaEnabled;
    }

    public Boolean getSafeModeEnabled() {
        return safeModeEnabled;
    }

    public void setSafeModeEnabled(Boolean safeModeEnabled) {
        this.safeModeEnabled = safeModeEnabled;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
