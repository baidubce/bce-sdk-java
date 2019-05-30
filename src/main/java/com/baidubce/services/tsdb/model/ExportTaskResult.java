package com.baidubce.services.tsdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Export task result.
 *
 * @author zhangweiliang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportTaskResult extends TaskResult {
    private long exportedDataPointsCount;

    private long exportedLinesCount;

    private long exportedTime;

    private String bucketName;

    private List<String> objectNames = Lists.newArrayList();

    public long getExportedDataPointsCount() {
        return exportedDataPointsCount;
    }

    public void setExportedDataPointsCount(long exportedDataPointsCount) {
        this.exportedDataPointsCount = exportedDataPointsCount;
    }

    public long getExportedLinesCount() {
        return exportedLinesCount;
    }

    public void setExportedLinesCount(long exportedLinesCount) {
        this.exportedLinesCount = exportedLinesCount;
    }

    public long getExportedTime() {
        return exportedTime;
    }

    public void setExportedTime(long exportedTime) {
        this.exportedTime = exportedTime;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public List<String> getObjectNames() {
        return objectNames;
    }

    public void setObjectNames(List<String> objectNames) {
        this.objectNames = objectNames;
    }
}
