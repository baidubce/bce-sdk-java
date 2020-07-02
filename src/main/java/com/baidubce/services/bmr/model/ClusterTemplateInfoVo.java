package com.baidubce.services.bmr.model;

import java.util.List;

public class ClusterTemplateInfoVo extends ClusterTemplateSummaryVo {
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
}
