package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainMediaDragRequest extends CdnRequest {
    private String domain;
    private MediaDragConf mediaDragConf;

    /**
     * @return mediaDragConf
     */
    public MediaDragConf getMediaDragConf() {
        return mediaDragConf;
    }

    /**
     * @param mediaDragConf Video drag settings
     */
    public void setMediaDragConf(MediaDragConf mediaDragConf) {
        this.mediaDragConf = mediaDragConf;
    }

    /**
     * @param mediaDragConf Video drag settings
     * @return thi object
     */
    public SetDomainMediaDragRequest withMediaDragConf(MediaDragConf mediaDragConf) {
        this.mediaDragConf = mediaDragConf;
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
    public SetDomainMediaDragRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
