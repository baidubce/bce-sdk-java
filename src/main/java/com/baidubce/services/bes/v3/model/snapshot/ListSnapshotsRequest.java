package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListSnapshotsRequest extends AbstractBesV3Request {
    private String clusterId;
    private Integer pageNo;
    private Integer pageSize;
    private String order;
    private String orderBy;
    private String snapshotName;
    private String type;
    private Long startTimestamp;
    private Long endTimestamp;
    private String snapshotStatus;
    private String restoreStatus;
}
