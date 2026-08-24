package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

@Data
public class ListIndicesRequest extends AbstractBesV3Request {
    private String clusterId;
    private String indexName;
    private String health;
    private Boolean includeSystem;
    private Integer pageNo;
    private Integer pageSize;
    private String orderBy;
    private String order;
}
