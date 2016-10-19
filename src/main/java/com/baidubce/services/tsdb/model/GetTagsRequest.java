package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for getting tags from Tsdb.
 */
public class GetTagsRequest extends AbstractBceRequest {
    
    private String metric;
    
    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
    
    public GetTagsRequest withMetric(String metric) {
        this.metric = metric;
        return this;
    }

    @Override
    public GetTagsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
