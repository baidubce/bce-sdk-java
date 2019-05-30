package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;
import java.util.Map;

/**
 * Represent the request for exporting datapoints.
 */
public class ExportDatapointsRequest extends AbstractBceRequest {

    private String databaseId;

    private String bosUrl;

    private String format;

    private List<String> metrics;

    private Map<String, List<TaskTagFilter>> tags;

    private Long start;

    private Long end;

    private boolean singleFile;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getBosUrl() {
        return bosUrl;
    }

    public void setBosUrl(String bosUrl) {
        this.bosUrl = bosUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    public Map<String, List<TaskTagFilter>> getTags() {
        return tags;
    }

    public void setTags(Map<String, List<TaskTagFilter>> tags) {
        this.tags = tags;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public boolean isSingleFile() {
        return singleFile;
    }

    public void setSingleFile(boolean singleFile) {
        this.singleFile = singleFile;
    }

    @Override
    public ExportDatapointsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
