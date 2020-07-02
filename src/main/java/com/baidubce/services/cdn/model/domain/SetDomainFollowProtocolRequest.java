package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/27
 */
public class SetDomainFollowProtocolRequest extends CdnRequest {
    private String domain;
    private boolean followProtocol;

    /**
     * @return followProtocol
     */
    public boolean isFollowProtocol() {
        return followProtocol;
    }

    /**
     * @param followProtocol Whether the back source protocol is consistent with the request protocol
     */
    public void setFollowProtocol(boolean followProtocol) {
        this.followProtocol = followProtocol;
    }

    /**
     * @param followProtocol Whether the back source protocol is consistent with the request protocol
     * @return this object
     */
    public SetDomainFollowProtocolRequest withFollowProtocol(boolean followProtocol) {
        this.followProtocol = followProtocol;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainFollowProtocolRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
