package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainTrafficLimitResponse extends CdnResponse {

    private TrafficLimit trafficLimit;

    public TrafficLimit getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(TrafficLimit trafficLimit) {
        this.trafficLimit = trafficLimit;
    }
}
