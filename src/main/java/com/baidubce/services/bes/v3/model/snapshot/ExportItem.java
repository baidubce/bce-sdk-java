package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

@Data
public class ExportItem {
    private String exportId;
    private String bucketName;
    private String path;
    private String mode;
    private String exportStatus;
    private Integer progress;
    private Long objectTotal;
    private Long objectFinished;
    private Long bytesTotal;
    private Long bytesFinished;
    private String errorMessage;
    private String remark;
    private String startTimestamp;
    private String endTimestamp;
}
