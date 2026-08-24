package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class SnapshotDetailItem {
    private String snapshotName;
    private String startTimestamp;
    private String endTimestamp;
    private String total;
    private String successful;
    private String failed;
}
