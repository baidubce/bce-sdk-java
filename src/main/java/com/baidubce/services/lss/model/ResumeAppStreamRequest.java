package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/6/26.
 */
public class ResumeAppStreamRequest extends AbstractBceRequest {
    private String app;
    private String stream;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public ResumeAppStreamRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public ResumeAppStreamRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    public ResumeAppStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ResumeAppStreamRequest {\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    stream: ").append(stream).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
