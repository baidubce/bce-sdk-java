package com.baidubce.services.vod.v2.model;

public class MediaFetchTaskInfo {

    private String url;
    private String bucket;
    private String key;
    private String coverUrl;
    private String highlightProjectId;

    private String errMsg;

    private MediaBasicInfo mediaBasicInfo;

    private String presetTaskId;
    private String workflowTaskId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getHighlightProjectId() {
        return highlightProjectId;
    }

    public void setHighlightProjectId(String highlightProjectId) {
        this.highlightProjectId = highlightProjectId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public MediaBasicInfo getMediaBasicInfo() {
        return mediaBasicInfo;
    }

    public void setMediaBasicInfo(MediaBasicInfo mediaBasicInfo) {
        this.mediaBasicInfo = mediaBasicInfo;
    }

    public String getPresetTaskId() {
        return presetTaskId;
    }

    public void setPresetTaskId(String presetTaskId) {
        this.presetTaskId = presetTaskId;
    }

    public String getWorkflowTaskId() {
        return workflowTaskId;
    }

    public void setWorkflowTaskId(String workflowTaskId) {
        this.workflowTaskId = workflowTaskId;
    }

}
