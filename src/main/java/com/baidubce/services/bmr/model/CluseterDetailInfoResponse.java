package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class CluseterDetailInfoResponse extends AbstractBceResponse {
    private String logUri;
    private String imageType;
    private String imageVersion;
    private Boolean autoTerminate;
    private Boolean terminationProtected;
    private List<Application> applications;
    private Reminder reminder;
    private ScaleStatus scaleStatus;
    private String jupyterUrl;
    private String yarnUrl;
    private String hueUrl;
    private boolean sendMessage;
    private String availabilityZone;
    private VpcInfo vpc;
    private SubnetInfo subnet;
    private BmrSecurityGroupVo systemSecurityGroup;
    private Boolean serviceHaEnabled;
    private Boolean safeModeEnabled;
    private List<InstanceGroupConfig> instanceGroups;
    private String imageDescription;
    private boolean copyable;
    private String id;
    private String name;
    private String payType;
    private ClusterStatus status;
    private String orderId;
    private Boolean alarmEnabled = Boolean.FALSE;
    private String executionPlanId;
    private boolean vpnEnabled;
    private String source;
    private String hmsUrl;
    private boolean expired;

    public String getLogUri() {
        return logUri;
    }

    public void setLogUri(String logUri) {
        this.logUri = logUri;
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

    public Boolean getAutoTerminate() {
        return autoTerminate;
    }

    public void setAutoTerminate(Boolean autoTerminate) {
        this.autoTerminate = autoTerminate;
    }

    public Boolean getTerminationProtected() {
        return terminationProtected;
    }

    public void setTerminationProtected(Boolean terminationProtected) {
        this.terminationProtected = terminationProtected;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public ScaleStatus getScaleStatus() {
        return scaleStatus;
    }

    public void setScaleStatus(ScaleStatus scaleStatus) {
        this.scaleStatus = scaleStatus;
    }

    public String getJupyterUrl() {
        return jupyterUrl;
    }

    public void setJupyterUrl(String jupyterUrl) {
        this.jupyterUrl = jupyterUrl;
    }

    public String getYarnUrl() {
        return yarnUrl;
    }

    public void setYarnUrl(String yarnUrl) {
        this.yarnUrl = yarnUrl;
    }

    public String getHueUrl() {
        return hueUrl;
    }

    public void setHueUrl(String hueUrl) {
        this.hueUrl = hueUrl;
    }

    public boolean isSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
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

    public List<InstanceGroupConfig> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<InstanceGroupConfig> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public boolean isCopyable() {
        return copyable;
    }

    public void setCopyable(boolean copyable) {
        this.copyable = copyable;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public ClusterStatus getStatus() {
        return status;
    }

    public void setStatus(ClusterStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getAlarmEnabled() {
        return alarmEnabled;
    }

    public void setAlarmEnabled(Boolean alarmEnabled) {
        this.alarmEnabled = alarmEnabled;
    }

    public String getExecutionPlanId() {
        return executionPlanId;
    }

    public void setExecutionPlanId(String executionPlanId) {
        this.executionPlanId = executionPlanId;
    }

    public boolean isVpnEnabled() {
        return vpnEnabled;
    }

    public void setVpnEnabled(boolean vpnEnabled) {
        this.vpnEnabled = vpnEnabled;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHmsUrl() {
        return hmsUrl;
    }

    public void setHmsUrl(String hmsUrl) {
        this.hmsUrl = hmsUrl;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
