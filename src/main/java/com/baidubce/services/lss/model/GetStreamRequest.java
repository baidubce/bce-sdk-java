package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/14.
 */
public class GetStreamRequest extends AbstractBceRequest {

    private String playDomain;

    private String app;

    private String stream;

    public GetStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public GetStreamRequest withPlayDomain(String playDomain) {
        this.playDomain = playDomain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public GetStreamRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public GetStreamRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetStreamRequest {\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    stream: ").append(stream).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
