package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class SnapshotPolicyUpdateRequest extends GenericGaiadbRequest {
    private String dataType;
    private String appID;
    private String clusterID;
    private List<String> dataBackupWeekDay;
    private String dataBackupTime;
    private List<Map<String, Object>> dataBackupRetainStrategys;
    private Integer logBackupRetainDays;
    private Map<String, Object> encryptStrategy;
    private Boolean logicalLogBackupEnable;
    private Integer logicalLogBackupRetainDays;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public List<String> getDataBackupWeekDay() {
        return dataBackupWeekDay;
    }

    public void setDataBackupWeekDay(List<String> dataBackupWeekDay) {
        this.dataBackupWeekDay = dataBackupWeekDay;
    }

    public String getDataBackupTime() {
        return dataBackupTime;
    }

    public void setDataBackupTime(String dataBackupTime) {
        this.dataBackupTime = dataBackupTime;
    }

    public List<Map<String, Object>> getDataBackupRetainStrategys() {
        return dataBackupRetainStrategys;
    }

    public void setDataBackupRetainStrategys(List<Map<String, Object>> dataBackupRetainStrategys) {
        this.dataBackupRetainStrategys = dataBackupRetainStrategys;
    }

    public Integer getLogBackupRetainDays() {
        return logBackupRetainDays;
    }

    public void setLogBackupRetainDays(Integer logBackupRetainDays) {
        this.logBackupRetainDays = logBackupRetainDays;
    }

    public Map<String, Object> getEncryptStrategy() {
        return encryptStrategy;
    }

    public void setEncryptStrategy(Map<String, Object> encryptStrategy) {
        this.encryptStrategy = encryptStrategy;
    }

    public Boolean getLogicalLogBackupEnable() {
        return logicalLogBackupEnable;
    }

    public void setLogicalLogBackupEnable(Boolean logicalLogBackupEnable) {
        this.logicalLogBackupEnable = logicalLogBackupEnable;
    }

    public Integer getLogicalLogBackupRetainDays() {
        return logicalLogBackupRetainDays;
    }

    public void setLogicalLogBackupRetainDays(Integer logicalLogBackupRetainDays) {
        this.logicalLogBackupRetainDays = logicalLogBackupRetainDays;
    }
}
