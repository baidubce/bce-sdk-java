package com.baidubce.services.scs.model.instance;

/**
 * The full version info of scs instance.
 */
public class ScsInstanceFullVersionInfo {

    private String proxyFullVersion;
    private String redisOrPegaFullVerison;
    private String proxyLatestFullVersion;
    private String redisOrPegaLatestFullVersion;
    private Boolean isProxyCanUpgrade;
    private Boolean isRedisOrPegaCanUpgrade;
    private Boolean isPegaCanRestart;

    public String getProxyFullVersion() {
        return proxyFullVersion;
    }

    public void setProxyFullVersion(String proxyFullVersion) {
        this.proxyFullVersion = proxyFullVersion;
    }

    public String getRedisOrPegaFullVerison() {
        return redisOrPegaFullVerison;
    }

    public void setRedisOrPegaFullVerison(String redisOrPegaFullVerison) {
        this.redisOrPegaFullVerison = redisOrPegaFullVerison;
    }

    public String getProxyLatestFullVersion() {
        return proxyLatestFullVersion;
    }

    public void setProxyLatestFullVersion(String proxyLatestFullVersion) {
        this.proxyLatestFullVersion = proxyLatestFullVersion;
    }

    public String getRedisOrPegaLatestFullVersion() {
        return redisOrPegaLatestFullVersion;
    }

    public void setRedisOrPegaLatestFullVersion(String redisOrPegaLatestFullVersion) {
        this.redisOrPegaLatestFullVersion = redisOrPegaLatestFullVersion;
    }

    public Boolean getIsProxyCanUpgrade() {
        return isProxyCanUpgrade;
    }

    public void setIsProxyCanUpgrade(Boolean isProxyCanUpgrade) {
        this.isProxyCanUpgrade = isProxyCanUpgrade;
    }

    public Boolean getIsRedisOrPegaCanUpgrade() {
        return isRedisOrPegaCanUpgrade;
    }

    public void setIsRedisOrPegaCanUpgrade(Boolean isRedisOrPegaCanUpgrade) {
        this.isRedisOrPegaCanUpgrade = isRedisOrPegaCanUpgrade;
    }

    public Boolean getIsPegaCanRestart() {
        return isPegaCanRestart;
    }

    public void setIsPegaCanRestart(Boolean isPegaCanRestart) {
        this.isPegaCanRestart = isPegaCanRestart;
    }
}
