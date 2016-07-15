package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/6/28.
 */
public class GetAppRequest extends AbstractBceRequest {
    private String app = null;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public GetAppRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public GetAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAppRequest {\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
