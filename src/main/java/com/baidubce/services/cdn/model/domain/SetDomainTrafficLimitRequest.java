package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class SetDomainTrafficLimitRequest extends AbstractBceRequest {

    private String domain;

    private TrafficLimit trafficLimit;

    public SetDomainTrafficLimitRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public SetDomainTrafficLimitRequest withTrafficLimit(TrafficLimit trafficLimit) {
        this.trafficLimit = trafficLimit;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public TrafficLimit getTrafficLimit() {
        return trafficLimit;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
