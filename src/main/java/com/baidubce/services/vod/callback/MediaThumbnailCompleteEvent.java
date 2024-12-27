package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.MediaThumbnailTaskInfo;

public class MediaThumbnailCompleteEvent extends AbstractMediaProcessCompleteEvent {

    private MediaThumbnailTaskInfo thumbnailTaskInfo;

    public MediaThumbnailTaskInfo getThumbnailTaskInfo() {
        return thumbnailTaskInfo;
    }

    public void setThumbnailTaskInfo(MediaThumbnailTaskInfo thumbnailTaskInfo) {
        this.thumbnailTaskInfo = thumbnailTaskInfo;
    }

}