package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * 离线模式指的是在资源过期回源的时候，如果源站异常，那么CDN会响应之前缓存的资源，响应客户端200，但是回源日志中还是显示5xx
 */
public class SetDomainOfflineModeRequest extends CdnRequest {

    private String domain;

    private boolean OfflineMode;

    public boolean isOfflineMode() {
        return OfflineMode;
    }

    public void setOfflineMode(boolean offlineMode) {
        OfflineMode = offlineMode;
    }

    /**
     * @param Offline
     * @return this object
     */
    public SetDomainOfflineModeRequest withOfflineMode(boolean Offline) {
        setOfflineMode(Offline);
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainOfflineModeRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
