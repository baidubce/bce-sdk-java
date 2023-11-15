package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;
import java.util.List;

public class CopyDomainTaskRequest extends CdnRequest {
    private String originDomain;
    private List<String> domains;
    private List<String> configs;

    public CopyDomainTaskRequest() {
    }

    public String getOriginDomain() {
        return originDomain;
    }

    public void setOriginDomain(String originDomain) {
        this.originDomain = originDomain;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getConfigs() {
        return configs;
    }

    public void setConfigs(List<String> configs) {
        this.configs = configs;
    }

    public CopyDomainTaskRequest withOriginDomain(String originDomain) {
        this.originDomain = originDomain;
        return this;
    }

    public CopyDomainTaskRequest withDomains(List<String> domains) {
        this.domains = domains;
        return this;
    }

    public CopyDomainTaskRequest withConfigs(List<String> configs) {
        this.configs = configs;
        return this;
    }
}
