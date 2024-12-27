package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaPresetTaskInfo {

    private String mediaId;
    private List<MediaTranscodeTaskInfo> transcodeTasks;
    private List<MediaThumbnailTaskInfo> thumbnailTasks;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public List<MediaTranscodeTaskInfo> getTranscodeTasks() {
        return transcodeTasks;
    }

    public void setTranscodeTasks(List<MediaTranscodeTaskInfo> transcodeTasks) {
        this.transcodeTasks = transcodeTasks;
    }

    public List<MediaThumbnailTaskInfo> getThumbnailTasks() {
        return thumbnailTasks;
    }

    public void setThumbnailTasks(List<MediaThumbnailTaskInfo> thumbnailTasks) {
        this.thumbnailTasks = thumbnailTasks;
    }

}
