package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListRestoresRequest extends AbstractBesV3Request {
    private String clusterId;
    private Long snapshotId;
    private Integer pageNo;
    private Integer pageSize;
    private String order;
    private String orderBy;
    private String restoreStatus;
}
