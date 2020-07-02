package com.baidubce.services.bmr.model;

import java.util.Date;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TemplateInfoResponse extends AbstractBceResponse {
    List<StepConfig> steps;
    private Boolean alarmEnabled;
    private Boolean autoTerminate;
    private String logUri;
    private String payType;
    private Reminder reminder;
    private Boolean sendMessage;
    private Boolean terminationProtected;
    private String availabilityZone;
    private VpcInfo vpc;
    private SubnetInfo subnet;
    private BmrSecurityGroupVo systemSecurityGroup;
    private Boolean serviceHaEnabled;
    private Boolean safeModeEnabled;
    private String imageDescription;
    private boolean isCopyable;
    private boolean abandoned;
    private List<Application> applications;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC") private Date
            creationDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC") private Date
            updateDateTime;
    private String id;
    private String name;
    private boolean shared;
    private String imageType;
    private String imageVersion;
    private List<InstanceGroupConfig> instanceGroups;

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<StepConfig> steps) {
        this.steps = steps;
    }

    public Boolean getAlarmEnabled() {
        return alarmEnabled;
    }

    public void setAlarmEnabled(Boolean alarmEnabled) {
        this.alarmEnabled = alarmEnabled;
    }

    public Boolean getAutoTerminate() {
        return autoTerminate;
    }

    public void setAutoTerminate(Boolean autoTerminate) {
        this.autoTerminate = autoTerminate;
    }

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public Boolean getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(Boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public Boolean getTerminationProtected() {
        return terminationProtected;
    }

    public void setTerminationProtected(Boolean terminationProtected) {
        this.terminationProtected = terminationProtected;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public VpcInfo getVpc() {
        return vpc;
    }

    public void setVpc(VpcInfo vpc) {
        this.vpc = vpc;
    }

    public SubnetInfo getSubnet() {
        return subnet;
    }

    public void setSubnet(SubnetInfo subnet) {
        this.subnet = subnet;
    }

    public BmrSecurityGroupVo getSystemSecurityGroup() {
        return systemSecurityGroup;
    }

    public void setSystemSecurityGroup(BmrSecurityGroupVo systemSecurityGroup) {
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

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public boolean isCopyable() {
        return isCopyable;
    }

    public void setCopyable(boolean copyable) {
        isCopyable = copyable;
    }

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
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
}
