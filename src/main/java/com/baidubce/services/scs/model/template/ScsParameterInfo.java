package com.baidubce.services.scs.model.template;

public class ScsParameterInfo {
    private String confName;
    private String confDefault;
    private String confValue;
    private Integer confType;
    private String confRange;
    private Integer confModule;
    private String confDesc;
    private Integer needReboot;
    private String confRedisVersion;
    private Integer confCacheVersion;
    private Integer confUserVisible;

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfDefault() {
        return confDefault;
    }

    public void setConfDefault(String confDefault) {
        this.confDefault = confDefault;
    }

    public String getConfValue() {
        return confValue;
    }

    public void setConfValue(String confValue) {
        this.confValue = confValue;
    }

    public Integer getConfType() {
        return confType;
    }

    public void setConfType(Integer confType) {
        this.confType = confType;
    }

    public String getConfRange() {
        return confRange;
    }

    public void setConfRange(String confRange) {
        this.confRange = confRange;
    }

    public Integer getConfModule() {
        return confModule;
    }

    public void setConfModule(Integer confModule) {
        this.confModule = confModule;
    }

    public String getConfDesc() {
        return confDesc;
    }

    public void setConfDesc(String confDesc) {
        this.confDesc = confDesc;
    }

    public Integer getNeedReboot() {
        return needReboot;
    }

    public void setNeedReboot(Integer needReboot) {
        this.needReboot = needReboot;
    }

    public String getConfRedisVersion() {
        return confRedisVersion;
    }

    public void setConfRedisVersion(String confRedisVersion) {
        this.confRedisVersion = confRedisVersion;
    }

    public Integer getConfCacheVersion() {
        return confCacheVersion;
    }

    public void setConfCacheVersion(Integer confCacheVersion) {
        this.confCacheVersion = confCacheVersion;
    }

    public Integer getConfUserVisible() {
        return confUserVisible;
    }

    public void setConfUserVisible(Integer confUserVisible) {
        this.confUserVisible = confUserVisible;
    }
}
