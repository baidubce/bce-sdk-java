package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/6/28.
 */
public class ListAppResponse extends AbstractBceResponse {
    List<GetAppResponse> apps = null;

    public List<GetAppResponse> getApps() {
        return apps;
    }

    public void setApps(List<GetAppResponse> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListAppResponse {\n");
        sb.append("    streams: ").append(apps).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
