package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListSnapshotsResponse extends AbstractBceResponse {
    private List<SnapshotItem> snapshots;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
    private String orderBy;
    private String order;
}
