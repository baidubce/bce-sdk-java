package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class SetDomainErrorPageRequest extends AbstractBceRequest {

    private String domain;
    private List<ErrorPage> errorPage;

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
    public SetDomainErrorPageRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return errorPage
     */
    public List<ErrorPage> getErrorPage() {
        return errorPage;
    }

    /**
     * @param errorPage error page info
     */
    public void setErrorPage(List<ErrorPage> errorPage) {
        this.errorPage = errorPage;
    }

    /**
     * @param errorPage error page list
     * @return this object
     */
    public SetDomainErrorPageRequest withErrorPage(List<ErrorPage> errorPage) {
        setErrorPage(errorPage);
        return this;
    }

    /**
     * @param page error page info
     * @return this object
     */
    public SetDomainErrorPageRequest addErrorPage(ErrorPage page) {
        if (errorPage == null) {
            errorPage = new ArrayList<ErrorPage>();
        }
        errorPage.add(page);
        return this;
    }

    /**
     * (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public SetDomainErrorPageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
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
