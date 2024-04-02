package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.Dimension;
import lombok.Data;

import java.util.List;

/**
 * create by pangyangyang on 2021/02/22
 */
@Data
public class SiteMetricDataQueryResponse extends AbstractBceResponse {

    private String namespace;

    private List<Dimension> dimensions;

    private List<TsdbQueryDataPoint> dataPoints;
}
