package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class CreateSnapshotRestoreRequest extends AbstractBesV3Request {
    private String clusterId;
    private Long snapshotId;
    private String destClusterId;
    private Boolean allIndex;
    private String restoreIndices;
    private String restoreIndexPattern;
    private String renamePattern;
    private String renameReplacement;
}
