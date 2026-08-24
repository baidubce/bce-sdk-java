package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class SnapshotDetailResponse extends AbstractBceResponse {
    private String historyId;
    private String configId;
    private String type;
    private String snapshotName;
    private String snapshotStatus;
    private String restoreId;
    private String restoreStatus;
    private String restoreStartTimestamp;
    private String totalSize;
    private String increSize;
    private String remark;
    private String expireAfter;
    private String expireAfterText;
    private String startTimestamp;
    private String endTimestamp;
    private String invalidTime;
    private String invalidReason;
    private String cleanupTime;
    private String cleanupReason;
    private String esVersion;
    private String snapshotShardsTotal;
    private String snapshotShardsSuccessful;
    private String snapshotShardsFailed;
    private String snapshotIndexData;
}
