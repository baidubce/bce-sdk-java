package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * create by changxing01 on 19/8/27
 */
public class GetUserDomainsRequest extends AbstractBceRequest {
    private String status;
    private String rule;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status filter domain status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return rule
     */
    public String getRule() {
        return rule;
    }

    /**
     * @param rule fuzzy matching content
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * @param status filter domain status
     * @return returns this object
     */
    public GetUserDomainsRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public GetUserDomainsRequest withRule(String rule) {
        this.rule = rule;
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
