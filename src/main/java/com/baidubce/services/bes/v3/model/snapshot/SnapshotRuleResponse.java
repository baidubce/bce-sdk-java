package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class SnapshotRuleResponse extends AbstractBceResponse {
    private String snapshotId;
    private String configId;
    private String snapshotName;
    private String snapshotStatus;
    private String expireAfter;
    private String expireAfterText;
    private Boolean indexDataReady;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
    private List<SnapshotRuleIndexItem> indices;
}
