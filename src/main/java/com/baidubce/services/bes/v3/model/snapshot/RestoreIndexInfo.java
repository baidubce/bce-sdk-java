package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class RestoreIndexInfo {
    private String index;
    private String sourceIndex;
    private String indexSize;
    private String indexPriSize;
}
