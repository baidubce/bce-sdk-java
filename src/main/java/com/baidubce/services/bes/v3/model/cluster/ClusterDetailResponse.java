package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ClusterDetailResponse extends AbstractBceResponse {
    private String clusterId;
    private String name;
    private String createTime;
    private String engine;
    private String engineType;
    private String version;
    private String kernelVersion;
    private String status;
    private ClusterHealth clusterHealth;
    private String region;
    private Integer logicalZonesCount;
    private List<String> logicalZones;
    private RunningTime runningTime;
    private Boolean deletionProtection;
    private String maintenanceTimeZone;
    private List<String> maintenancePeriods;
    private String maintenanceStartTime;
    private String maintenanceEndTime;
    private String payment;
    private Boolean isPrepayToPostpay;
    private String expirationTime;
    private Boolean isAutoRenew;
    private AutoRenew autoRenew;
    private ClusterBilling billing;
    private String adminUserName;
    private Boolean enablePublicAccess;
    private String privateEndpoint;
    private String publicEndpoint;
    private Boolean enableHttps;
    private Boolean enableVisualPublicAccess;
    private String visualPrivateEndpoint;
    private String visualPublicEndpoint;
    private Boolean enableVisualHttps;
    private List<String> publicIpWhitelist;
    private List<String> privateIpWhitelist;
    private List<String> visualPublicIpWhitelist;
    private List<String> visualPrivateIpWhitelist;
    private Boolean enableCerebro;
    private String cerebroPublicEndpoint;
    private String cerebroPrivateEndpoint;
    private String vpcId;
    private String subnetId;
    private List<ClusterDetailNodeSpec> nodeSpecs;
    private List<Tag> tags;
    private Boolean inRecycleBin;
    private String deleteExpireTime;
    private Integer recycleCount;

}
