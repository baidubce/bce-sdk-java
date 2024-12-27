package com.baidubce.services.bcm.model.metrics;

import com.baidubce.model.AbstractBceResponse;
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
public class TsdbQueryMetaData extends AbstractBceResponse {

    private String requestId;
    private String userId;
    private String serviceName;
    private String metricName;
    private String resourceId;
    private List<Dimension> dimensions;
    private List<TsdbDataQueryResult.DataPoint> dataPoints;
}
