package com.baidubce.services.bcm;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.model.Dimension;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultiDimensionalLatestMetricsRequest extends AbstractBceRequest {
    @NotNull
    private String userId;
    @NotNull
    private String scope;

    private String region;

    /* 子类型 */
    @JsonAlias(value = {"typename", "resourceType", "type"})
    private String typename;

    private List<String> metricNames;

    private List<String> statistics;

    private List<Dimension> dimensions = new ArrayList<Dimension>();

    private String timestamp;

    private int cycle = 60;

    @Override
    public MultiDimensionalLatestMetricsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
