package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * The request for setting the originConfig of a domain.
 */
public class SetDomainOriginConfigRequest extends CdnRequest {
    /**
     * The domain to configure.
     */
    @JsonIgnore
    private String domain;

    /**
     * The origin server list.
     */
    private List<OriginItem> originConfig;

    /**
     * @param domain
     * @return this object
     */
    public SetDomainOriginConfigRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param originItem
     * @return this object
     */
    public SetDomainOriginConfigRequest addOriginItem(OriginItem originItem) {
        if (this.originConfig == null) {
            this.originConfig = new ArrayList<OriginItem>();
        }
        this.originConfig.add(originItem);
        return this;
    }

    /**
     * @return originConfig
     */
    public List<OriginItem> getOriginConfig() {
        return originConfig;
    }

    /**
     * @param originConfig
     */
    public void setOriginConfig(List<OriginItem> originConfig) {
        this.originConfig = originConfig;
    }
}
