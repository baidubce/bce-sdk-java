package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * create by changxing01 on 19/8/27
 */
public class SetDomainAccessLimitRequest extends AbstractBceRequest {

    private String domain;
    private AccessLimit accessLimit;

    /**
     * @return accessLimit
     */
    public AccessLimit getAccessLimit() {
        return accessLimit;
    }

    /**
     * @param accessLimit IP access frequency limit control information
     */
    public void setAccessLimit(AccessLimit accessLimit) {
        this.accessLimit = accessLimit;
    }

    /**
     * @param accessLimit IP access frequency limit control information
     * @return this object
     */
    public SetDomainAccessLimitRequest withAccessLimit(AccessLimit accessLimit) {
        this.accessLimit = accessLimit;
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
    public SetDomainAccessLimitRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     *  (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     *  (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
