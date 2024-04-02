package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.Statistics;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GetMetricDataForApplicationRequest extends AbstractBceRequest{
    private String userId;
    private String appName;
    private String taskName;
    private List<String> instances;
    private String metricName;
    private int cycle;
    private String startTime;
    private String endTime;
    private Map<String, List<String>> dimensions;
    private Statistics statistics;
    private Boolean aggrData = false;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}