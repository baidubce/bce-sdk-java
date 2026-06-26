package com.baidubce.services.scs.model.instance;

/**
 * The feature switches of scs instance.
 */
public class ScsFeatureSwitches {

    private Boolean groupModify;
    private Boolean crossAzNearest;
    private Boolean proxyUpgradeSupport;
    private Boolean recoverInOriginSupport;
    private Boolean recoverInNewSupport;
    private Boolean supportSentinelSwitch;
    private Boolean whitelistGroupSupport;
    private Boolean bandwidthModify;
    private Boolean shadowBackupSupport;

    public Boolean getGroupModify() {
        return groupModify;
    }

    public void setGroupModify(Boolean groupModify) {
        this.groupModify = groupModify;
    }

    public Boolean getCrossAzNearest() {
        return crossAzNearest;
    }

    public void setCrossAzNearest(Boolean crossAzNearest) {
        this.crossAzNearest = crossAzNearest;
    }

    public Boolean getProxyUpgradeSupport() {
        return proxyUpgradeSupport;
    }

    public void setProxyUpgradeSupport(Boolean proxyUpgradeSupport) {
        this.proxyUpgradeSupport = proxyUpgradeSupport;
    }

    public Boolean getRecoverInOriginSupport() {
        return recoverInOriginSupport;
    }

    public void setRecoverInOriginSupport(Boolean recoverInOriginSupport) {
        this.recoverInOriginSupport = recoverInOriginSupport;
    }

    public Boolean getRecoverInNewSupport() {
        return recoverInNewSupport;
    }

    public void setRecoverInNewSupport(Boolean recoverInNewSupport) {
        this.recoverInNewSupport = recoverInNewSupport;
    }

    public Boolean getSupportSentinelSwitch() {
        return supportSentinelSwitch;
    }

    public void setSupportSentinelSwitch(Boolean supportSentinelSwitch) {
        this.supportSentinelSwitch = supportSentinelSwitch;
    }

    public Boolean getWhitelistGroupSupport() {
        return whitelistGroupSupport;
    }

    public void setWhitelistGroupSupport(Boolean whitelistGroupSupport) {
        this.whitelistGroupSupport = whitelistGroupSupport;
    }

    public Boolean getBandwidthModify() {
        return bandwidthModify;
    }

    public void setBandwidthModify(Boolean bandwidthModify) {
        this.bandwidthModify = bandwidthModify;
    }

    public Boolean getShadowBackupSupport() {
        return shadowBackupSupport;
    }

    public void setShadowBackupSupport(Boolean shadowBackupSupport) {
        this.shadowBackupSupport = shadowBackupSupport;
    }
}
