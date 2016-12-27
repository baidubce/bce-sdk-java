package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/6/28.
 */
public class ListAppRequest extends AbstractBceRequest {

    public ListAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListAppRequest {\n");
        sb.append("}\n");
        return sb.toString();
    }
}
