package com.baidubce.services.tsdb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Export task params.
 *
 * @author zhangweiliang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportTaskParams extends TaskParams {

    private String bosUrl;

    private List<String> metrics;

    private Map<String, List<TaskTagFilter>> tags;

    private long start;

    private long end;

    private boolean singleFile;

    private String format;

    public String getBosUrl() {
        return bosUrl;
    }

    public void setBosUrl(String bosUrl) {
        this.bosUrl = bosUrl;
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isSingleFile() {
        return singleFile;
    }

    public void setSingleFile(boolean singleFile) {
        this.singleFile = singleFile;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
