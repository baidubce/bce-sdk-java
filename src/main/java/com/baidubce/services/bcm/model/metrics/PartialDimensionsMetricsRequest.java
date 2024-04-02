package com.baidubce.services.bcm.model.metrics;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.Dimension;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartialDimensionsMetricsRequest extends AbstractBceRequest {
    private String userId;
    private String startTime;
    private String endTime;
    private List<String> statistics;
    private Integer cycle;
    private List<Dimension> dimensions;
    private String scope;
    private String resourceType;
    private String metricName;
    private String region;
    private Integer pageNo;
    private Integer pageSize;
    @Override
    public PartialDimensionsMetricsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}