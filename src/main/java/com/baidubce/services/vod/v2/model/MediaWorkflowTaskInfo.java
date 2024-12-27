package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaWorkflowTaskInfo {

    private String mediaId;
    private String workflowId;
    private MediaWorkflowStartNodeTaskInfo startNodeTaskInfo;
    private List<MediaWorkflowTranscodeNodeTaskInfo> transcodeNodeTaskInfos;
    private List<MediaWorkflowThumbnailNodeTaskInfo> thumbnailNodeTaskInfos;
    private List<MediaWorkflowBlackBorderDetectNodeTaskInfo> blackBorderDetectNodeTaskInfos;
    private List<MediaWorkflowSubtitleNodeTaskInfo> subtitleNodeTaskInfos;
    private List<MediaWorkflowAnalysisNodeTaskInfo> analysisNodeTaskInfos;
    private List<MediaWorkflowRegulationNodeTaskInfo> regulationNodeTaskInfos;
    private MediaWorkflowPublishNodeTaskInfo publishNodeTaskInfo;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public MediaWorkflowStartNodeTaskInfo getStartNodeTaskInfo() {
        return startNodeTaskInfo;
    }

    public void setStartNodeTaskInfo(MediaWorkflowStartNodeTaskInfo startNodeTaskInfo) {
        this.startNodeTaskInfo = startNodeTaskInfo;
    }

    public List<MediaWorkflowTranscodeNodeTaskInfo> getTranscodeNodeTaskInfos() {
        return transcodeNodeTaskInfos;
    }

    public void setTranscodeNodeTaskInfos(List<MediaWorkflowTranscodeNodeTaskInfo> transcodeNodeTaskInfos) {
        this.transcodeNodeTaskInfos = transcodeNodeTaskInfos;
    }

    public List<MediaWorkflowThumbnailNodeTaskInfo> getThumbnailNodeTaskInfos() {
        return thumbnailNodeTaskInfos;
    }

    public void setThumbnailNodeTaskInfos(List<MediaWorkflowThumbnailNodeTaskInfo> thumbnailNodeTaskInfos) {
        this.thumbnailNodeTaskInfos = thumbnailNodeTaskInfos;
    }

    public List<MediaWorkflowBlackBorderDetectNodeTaskInfo> getBlackBorderDetectNodeTaskInfos() {
        return blackBorderDetectNodeTaskInfos;
    }

    public void setBlackBorderDetectNodeTaskInfos(List<MediaWorkflowBlackBorderDetectNodeTaskInfo> blackBorderDetectNodeTaskInfos) {
        this.blackBorderDetectNodeTaskInfos = blackBorderDetectNodeTaskInfos;
    }

    public List<MediaWorkflowSubtitleNodeTaskInfo> getSubtitleNodeTaskInfos() {
        return subtitleNodeTaskInfos;
    }

    public void setSubtitleNodeTaskInfos(List<MediaWorkflowSubtitleNodeTaskInfo> subtitleNodeTaskInfos) {
        this.subtitleNodeTaskInfos = subtitleNodeTaskInfos;
    }

    public List<MediaWorkflowAnalysisNodeTaskInfo> getAnalysisNodeTaskInfos() {
        return analysisNodeTaskInfos;
    }

    public void setAnalysisNodeTaskInfos(List<MediaWorkflowAnalysisNodeTaskInfo> analysisNodeTaskInfos) {
        this.analysisNodeTaskInfos = analysisNodeTaskInfos;
    }

    public List<MediaWorkflowRegulationNodeTaskInfo> getRegulationNodeTaskInfos() {
        return regulationNodeTaskInfos;
    }

    public void setRegulationNodeTaskInfos(List<MediaWorkflowRegulationNodeTaskInfo> regulationNodeTaskInfos) {
        this.regulationNodeTaskInfos = regulationNodeTaskInfos;
    }

    public MediaWorkflowPublishNodeTaskInfo getPublishNodeTaskInfo() {
        return publishNodeTaskInfo;
    }

    public void setPublishNodeTaskInfo(MediaWorkflowPublishNodeTaskInfo publishNodeTaskInfo) {
        this.publishNodeTaskInfo = publishNodeTaskInfo;
    }

}
