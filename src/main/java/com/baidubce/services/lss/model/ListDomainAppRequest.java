package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListDomainAppRequest extends AbstractBceRequest {

    private String playDomain = null;

    public ListDomainAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDomainAppRequest{\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
