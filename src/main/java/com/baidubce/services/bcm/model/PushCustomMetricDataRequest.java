package com.baidubce.services.bcm.model;


import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * push custom metric data request
 *
 * @Author: wanglu51
 * @Date: 2023/6/12 10:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushCustomMetricDataRequest extends AbstractBceRequest {
    @JsonIgnore
    private String userId;
    private String namespace;
    private String metricName;
    private List<Dimension> dimensions;
    private Double value;
    private StatisticValue statisticValues;
    private String timestamp;

    @Override
    public PushCustomMetricDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
