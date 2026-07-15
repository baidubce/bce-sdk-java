package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * The response of querying the originConfig of a domain.
 */
public class GetDomainOriginConfigResponse extends CdnResponse {
    /**
     * The origin server list.
     */
    private List<OriginItem> originConfig;

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
