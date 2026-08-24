package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class SnapshotRuleIndexItem {
    private String indexName;
    private String totalSize;
    private String increSize;
}
