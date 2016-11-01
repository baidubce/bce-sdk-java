package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListDomainAppResponse extends AbstractBceResponse {

    List<String> appList = null;

    public List<String> getAppList() {
        return appList;
    }

    public void setAppList(List<String> appList) {
        this.appList = appList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDomainAppResponse {\n");
        sb.append("    appList: ").append(appList).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
