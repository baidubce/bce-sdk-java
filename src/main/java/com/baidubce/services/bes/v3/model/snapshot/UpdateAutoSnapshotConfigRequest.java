package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class UpdateAutoSnapshotConfigRequest extends AbstractBesV3Request {
    private String clusterId;
    private Long configId;
    private String name;
    private String snapshotType;
    private String period;
    private String expireAfter;
    private String remark;
    private Boolean allIndex;
    private String snapshotIndices;
    private String snapshotIndexPattern;
    private Boolean enabled;
}
