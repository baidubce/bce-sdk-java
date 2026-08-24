package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class CreateManualSnapshotRequest extends AbstractBesV3Request {
    private String clusterId;
    private String name;
    private String snapshotType;
    private String expireAfter;
    private String remark;
    private Boolean allIndex;
    private String snapshotIndices;
    private String snapshotIndexPattern;
}
