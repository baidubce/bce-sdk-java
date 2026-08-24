package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class SnapshotConfigDetailResponse extends AbstractBceResponse {
    private String configId;
    private String type;
    private String name;
    private String snapshotType;
    private String period;
    private String expireAfter;
    private String remark;
    private String repository;
    private String repositoryBucket;
    private String repositoryBasePath;
    private Boolean enabled;
    private String createTimestamp;
    private String updateTimestamp;
    private String enableTimestamp;
    private Boolean allIndex;
    private String snapshotIndices;
    private String snapshotIndexPattern;
    private String snapshotStatus;
    private String snapshotStartTimestamp;
    private String restoreStatus;
    private String restoreStartTimestamp;
}
