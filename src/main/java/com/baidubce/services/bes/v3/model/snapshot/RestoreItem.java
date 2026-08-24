package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class RestoreItem {
    private String historyId;
    private String restoreId;
    private String destClusterId;
    private String destClusterName;
    private String destEngineVersion;
    private String destKernelVersion;
    private String restoreStatus;
    private RestoreDetailItem restoreDetail;
}
