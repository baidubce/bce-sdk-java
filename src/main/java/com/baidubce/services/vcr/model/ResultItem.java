/*
 * Copyright 2017 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.vcr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A specific item in CheckResult.
 */
public class ResultItem {
    private String subType;
    private String target;
    private Integer timeInSeconds;
    private Integer startTimeInSeconds;
    private Integer endTimeInSeconds;
    private String liveTime;
    private String startTime;
    private String endTime;
    private Double confidence;
    private String label;
    private String extra;
    private Evidence evidence;

    private List<List<Integer>> positions = new ArrayList<List<Integer>>();

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Integer timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public Integer getStartTimeInSeconds() {
        return startTimeInSeconds;
    }

    public void setStartTimeInSeconds(Integer startTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
    }

    public Integer getEndTimeInSeconds() {
        return endTimeInSeconds;
    }

    public void setEndTimeInSeconds(Integer endTimeInSeconds) {
        this.endTimeInSeconds = endTimeInSeconds;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public List<List<Integer>> getPositions() {
        return positions;
    }

    public void setPositions(List<List<Integer>> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultItem{");
        sb.append("subType='").append(subType).append('\'');
        sb.append(", target='").append(target).append('\'');
        sb.append(", timeInSeconds=").append(timeInSeconds);
        sb.append(", startTimeInSeconds=").append(startTimeInSeconds);
        sb.append(", endTimeInSeconds=").append(endTimeInSeconds);
        sb.append(", liveTime='").append(liveTime).append('\'');
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", confidence=").append(confidence);
        sb.append(", label='").append(label).append('\'');
        sb.append(", extra='").append(extra).append('\'');
        sb.append(", evidence=").append(evidence).append('\'');
        sb.append(", positions=").append(positions).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
