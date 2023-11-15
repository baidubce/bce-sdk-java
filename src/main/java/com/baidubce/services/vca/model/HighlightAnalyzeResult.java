/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created on 2022/11/15.
 *
 * @author Zhangli Chen (chenzhangli01@baidu.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HighlightAnalyzeResult {

    private List<HighlightClip> results;

    public List<HighlightClip> getResults() {
        return results;
    }

    public void setResults(List<HighlightClip> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HighlightAnalyzeResult{");
        sb.append("results=").append(results).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HighlightClip {

        private double score;

        private Long startTimeInMS;

        private Long endTimeInMS;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Long getStartTimeInMS() {
            return startTimeInMS;
        }

        public void setStartTimeInMS(Long startTimeInMS) {
            this.startTimeInMS = startTimeInMS;
        }

        public Long getEndTimeInMS() {
            return endTimeInMS;
        }

        public void setEndTimeInMS(Long endTimeInMS) {
            this.endTimeInMS = endTimeInMS;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("HighlightClip{");
            sb.append("score=").append(score).append('\'');
            sb.append(", startTimeInMS='").append(startTimeInMS).append('\'');
            sb.append(", endTimeInMS='").append(endTimeInMS).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
