/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Vca ResultItem model.
 *
 * @author houshunwei
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultItem {
    private String attribute;
    private Double confidence;
    private String source;
    private List<TimeInSeconds> time;
    private String version;

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<TimeInSeconds> getTime() {
        return time;
    }

    public void setTime(List<TimeInSeconds> time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultItem{");
        sb.append("confidence=").append(confidence);
        sb.append(", attribute='").append(attribute).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TimeInSeconds {
        private Integer start;
        private Integer end;

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getEnd() {
            return end;
        }

        public void setEnd(Integer end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "TimeInSeconds{"
                    + "start=" + start
                    + ", end=" + end
                    + '}';
        }
    }
}
