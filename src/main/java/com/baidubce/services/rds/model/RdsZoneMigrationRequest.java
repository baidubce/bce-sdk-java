package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Request of instance Free zone migration
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsZoneMigrationRequest extends AbstractBceRequest {

    private String instanceId;
    private String masterAzone;
    private String backupAzone;
    private List<String> zoneNames=new ArrayList<String>();
    private List<RdsSubnetMap>subnets=new ArrayList<RdsSubnetMap>();
    private String effectiveTime;

    public String getInstanceId() {
        return instanceId;
    }
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMasterAzone() {
        return masterAzone;
    }
    public void setMasterAzone(String masterAzone) {
        this.masterAzone = masterAzone;
    }

    public String getBackupAzone() {
        return backupAzone;
    }
    public void setBackupAzone(String backupAzone) {
        this.backupAzone = backupAzone;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }
    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public List<RdsSubnetMap> getSubnets() {
        return subnets;
    }
    public void setSubnets(List<RdsSubnetMap> subnets) {
        this.subnets = subnets;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
