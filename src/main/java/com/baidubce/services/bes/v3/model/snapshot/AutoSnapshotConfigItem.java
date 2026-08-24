package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class AutoSnapshotConfigItem {
    private String configId;
    private String type;
    private String name;
    private String snapshotType;
    private String period;
    private String expireAfter;
    private String remark;
    private Boolean enabled;
    private Boolean allIndex;
    private String snapshotIndices;
    private String snapshotIndexPattern;
    private String createTimestamp;
    private String updateTimestamp;
    private String snapshotStatus;
    private String snapshotStartTimestamp;
    private String restoreStatus;
    private String restoreStartTimestamp;
}
