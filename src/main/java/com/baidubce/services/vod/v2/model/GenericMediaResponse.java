package com.baidubce.services.vod.v2.model;

import com.baidubce.model.AbstractBceResponse;

public class GenericMediaResponse extends AbstractBceResponse {

    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
