package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get data size tendency for a cluster.
 */
@Data
public class DataSizeTendencyRequest extends AbstractBesV2Request {
    private String clusterId;
    private String indexPrefix;
    private String datePattern;
    private Integer times;
    private String timeUnit;
}
