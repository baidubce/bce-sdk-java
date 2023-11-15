package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetDomainUrlRulesResponse extends CdnResponse {
    private List<UrlRule> urlRules;

    public GetDomainUrlRulesResponse() {
    }

    public List<UrlRule> getUrlRules() {
        return urlRules;
    }

    public void setUrlRules(List<UrlRule> urlRules) {
        this.urlRules = urlRules;
    }
}
