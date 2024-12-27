package com.baidubce.services.bcm.model.metrics;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * create by pangyangyang on 2024/04/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TsdbDimensionTopQuery extends AbstractBceRequest  {

    private String requestId;
    private String userId;
    private String scope;
    private String region;
    private String metricName;
    private String statistics;
    private String startTime;
    private String endTime;
    private Map<String, String> dimensions;
    private Set<String> labels;
    private int topNum;
    private String order;
    private int cycle;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
