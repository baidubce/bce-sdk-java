package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.ArrayList;
import java.util.List;

public class SetDomainUrlRulesRequest extends CdnRequest {
    private String domain;
    private List<UrlRule> urlRules;


    public SetDomainUrlRulesRequest() {
    }

    public List<UrlRule> getUrlRules() {
        return urlRules;
    }

    public void setUrlRules(List<UrlRule> urlRules) {
        this.urlRules = urlRules;
    }

    public SetDomainUrlRulesRequest withUrlRules(List<UrlRule> urlRules) {
        this.urlRules = urlRules;
        return this;
    }

    public SetDomainUrlRulesRequest addUrlRule(UrlRule urlRule) {
        if (this.urlRules == null) {
            this.urlRules = new ArrayList<UrlRule>();
        }
        this.urlRules.add(urlRule);
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
    public SetDomainUrlRulesRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
