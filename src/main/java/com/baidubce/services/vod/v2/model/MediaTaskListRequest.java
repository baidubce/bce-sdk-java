package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class MediaTaskListRequest extends AbstractBceRequest {

    private String marker;
    private int maxSize = 10;
    private String taskId;
    private String mediaId;
    private String status;
    private String beginTime;
    private String endTime;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaTaskListRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public MediaTaskListRequest withMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public MediaTaskListRequest withTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public MediaTaskListRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public MediaTaskListRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public MediaTaskListRequest withBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public MediaTaskListRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

}
