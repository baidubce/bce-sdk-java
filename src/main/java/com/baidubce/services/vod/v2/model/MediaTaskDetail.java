package com.baidubce.services.vod.v2.model;

import com.baidubce.model.AbstractBceResponse;

public class MediaTaskDetail extends AbstractBceResponse {

    private String taskId;
    private String type;
    private String status;
    private String createTime;
    private String finishTime;

    private MediaWorkflowTaskInfo mediaWorkflowTaskInfo;
    private MediaPresetTaskInfo mediaPresetTaskInfo;
    private ComposeTaskInfo composeTaskInfo;
    private MediaFetchTaskInfo mediaFetchTaskInfo;
    private HighlightUnderstandTaskInfo highlightUnderstandTaskInfo;
    private MediaDwmDetectTaskInfo mediaDwmDetectTaskInfo;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public MediaWorkflowTaskInfo getMediaWorkflowTaskInfo() {
        return mediaWorkflowTaskInfo;
    }

    public void setMediaWorkflowTaskInfo(MediaWorkflowTaskInfo mediaWorkflowTaskInfo) {
        this.mediaWorkflowTaskInfo = mediaWorkflowTaskInfo;
    }

    public MediaPresetTaskInfo getMediaPresetTaskInfo() {
        return mediaPresetTaskInfo;
    }

    public void setMediaPresetTaskInfo(MediaPresetTaskInfo mediaPresetTaskInfo) {
        this.mediaPresetTaskInfo = mediaPresetTaskInfo;
    }

    public ComposeTaskInfo getComposeTaskInfo() {
        return composeTaskInfo;
    }

    public void setComposeTaskInfo(ComposeTaskInfo composeTaskInfo) {
        this.composeTaskInfo = composeTaskInfo;
    }

    public MediaFetchTaskInfo getMediaFetchTaskInfo() {
        return mediaFetchTaskInfo;
    }

    public void setMediaFetchTaskInfo(MediaFetchTaskInfo mediaFetchTaskInfo) {
        this.mediaFetchTaskInfo = mediaFetchTaskInfo;
    }

    public HighlightUnderstandTaskInfo getHighlightUnderstandTaskInfo() {
        return highlightUnderstandTaskInfo;
    }

    public void setHighlightUnderstandTaskInfo(HighlightUnderstandTaskInfo highlightUnderstandTaskInfo) {
        this.highlightUnderstandTaskInfo = highlightUnderstandTaskInfo;
    }

    public MediaDwmDetectTaskInfo getMediaDwmDetectTaskInfo() {
        return mediaDwmDetectTaskInfo;
    }

    public void setMediaDwmDetectTaskInfo(MediaDwmDetectTaskInfo mediaDwmDetectTaskInfo) {
        this.mediaDwmDetectTaskInfo = mediaDwmDetectTaskInfo;
    }

}
