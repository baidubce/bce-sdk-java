package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListStreamRequest extends AbstractBceRequest {

    private String playDomain = null;

    private String status = null;

    private String marker = null;

    private Integer maxSize = null;

    public ListStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public ListStreamRequest withPlayDomain(String playDomain) {
        this.playDomain = playDomain;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ListStreamRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public ListStreamRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public ListStreamRequest withMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListStreamRequest {\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    marker: ").append(marker).append("\n");
        sb.append("    maxSize: ").append(maxSize).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
