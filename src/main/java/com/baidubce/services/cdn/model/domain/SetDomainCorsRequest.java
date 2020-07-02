package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SetDomainCorsRequest extends AbstractBceRequest {
    private String domain;
    private Cors cors;

    /**
     * @return cors
     */
    public Cors getCors() {
        return cors;
    }

    /**
     * @param cors the cors config of domain
     */
    public void setCors(Cors cors) {
        this.cors = cors;
    }

    /**
     * @param cors the cors config of domain
     * @return this object
     */
    public SetDomainCorsRequest withCors(Cors cors) {
        this.cors = cors;
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
    public SetDomainCorsRequest withDomain(String domain) {
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
