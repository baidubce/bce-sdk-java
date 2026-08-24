package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

@Data
public class ListMonitorIndicesRequest extends AbstractBesV3Request {
    private String clusterId;
    private String indexName;
    private Boolean includeSystem;
    private Integer pageNo;
    private Integer pageSize;
}
