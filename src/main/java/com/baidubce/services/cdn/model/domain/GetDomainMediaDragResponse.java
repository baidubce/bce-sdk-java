package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainMediaDragResponse extends CdnResponse {
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
}
