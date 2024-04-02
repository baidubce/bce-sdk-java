package com.baidubce.services.bcm.model.metrics;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.Dimension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiDimensionalMetricsRequest extends AbstractBceRequest {
    private String userId;

    private String scope;

    private String region;

    private String type = "Instance";

    private String startTime;

    private String endTime;

    private List<String> metricNames;

    private List<String> statistics;

    private List<List<Dimension>> dimensions;

    private int cycle = 60;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
