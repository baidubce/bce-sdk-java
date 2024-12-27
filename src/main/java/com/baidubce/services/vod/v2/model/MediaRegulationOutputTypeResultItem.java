package com.baidubce.services.vod.v2.model;

public class MediaRegulationOutputTypeResultItem {

    private String subType;
    private Integer timeInSeconds;
    private Integer startTimeInSeconds;
    private Integer endTimeInSeconds;
    private Double confidence;
    private String label;
    private String extra;
    private MediaRegulationOutputTypeResultItemEvidence evidence;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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

    public MediaRegulationOutputTypeResultItemEvidence getEvidence() {
        return evidence;
    }

    public void setEvidence(MediaRegulationOutputTypeResultItemEvidence evidence) {
        this.evidence = evidence;
    }

}
