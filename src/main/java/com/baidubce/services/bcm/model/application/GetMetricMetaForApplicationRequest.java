package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class GetMetricMetaForApplicationRequest extends AbstractBceRequest{
    private String userId;
    private String appName;
    private String taskName;
    private List<String> instances;
    private String metricName;
    private List<String> dimensionKeys;
    
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}