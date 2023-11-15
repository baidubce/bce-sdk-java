package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.OriginPeer;

import java.util.List;

public class GetDomainOriginResponse extends CdnResponse {
    private List<OriginPeer> origin;
    private String defaultHost;
    private Boolean follow302;

    public GetDomainOriginResponse() {
    }

    public List<OriginPeer> getOrigin() {
        return origin;
    }

    public void setOrigin(List<OriginPeer> origin) {
        this.origin = origin;
    }

    public String getDefaultHost() {
        return defaultHost;
    }

    public void setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
    }

    public Boolean getFollow302() {
        return follow302;
    }

    public void setFollow302(Boolean follow302) {
        this.follow302 = follow302;
    }
}
