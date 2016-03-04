package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;

public class InternalCreateMediaResponse extends AbstractBceResponse {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InternalCreateMediaResponse {");
        sb.append("mediaId='").append(mediaId).append('\'');
        sb.append("}\n");
        return sb.toString();
    }

}
