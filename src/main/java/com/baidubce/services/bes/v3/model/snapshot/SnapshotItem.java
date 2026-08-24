package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class SnapshotItem {
    private String historyId;
    private String configId;
    private String type;
    private String snapshotStatus;
    private String restoreId;
    private String restoreStatus;
    private String restoreStartTimestamp;
    private String totalSize;
    private String increSize;
    private String remark;
    private SnapshotDetailItem snapshotDetail;
    private RestoreDetailItem restoreDetail;
}
