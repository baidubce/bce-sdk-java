package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

import java.util.List;

@Data
public class RestoreDetailItem {
    private String restoreShardsTotal;
    private String restoreShardsUnassigned;
    private String restoreShardsInitializing;
    private String restoreShardsStarted;
    private String startTimestamp;
    private String endTimestamp;
    private String restoreIndices;
    private String restoreIndexPattern;
    private String renamePattern;
    private String renameReplacement;
    private List<RestoreIndexInfo> restoreIndexInfoList;
}
