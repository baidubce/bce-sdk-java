package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/6/28.
 */
public class ListAppStreamsRequest extends AbstractBceRequest {
    private String app = null;
    private String status = null;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ListAppStreamsRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public ListAppStreamsRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public ListAppStreamsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListAppStreamsRequest {\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
