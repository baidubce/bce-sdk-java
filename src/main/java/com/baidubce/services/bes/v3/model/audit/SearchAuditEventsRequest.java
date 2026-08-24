package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class SearchAuditEventsRequest extends AbstractBesV3Request {
    private String clusterId;
    private String startTime;
    private String endTime;
    private String category;
    private String indexName;
    private Integer pageSize;
    private String cursor;
}
