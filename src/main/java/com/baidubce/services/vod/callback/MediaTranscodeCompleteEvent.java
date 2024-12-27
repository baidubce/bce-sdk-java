package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.MediaTranscodeTaskInfo;

public class MediaTranscodeCompleteEvent extends AbstractMediaProcessCompleteEvent {

    private MediaTranscodeTaskInfo transcodeTaskInfo;

    public MediaTranscodeTaskInfo getTranscodeTaskInfo() {
        return transcodeTaskInfo;
    }

    public void setTranscodeTaskInfo(MediaTranscodeTaskInfo transcodeTaskInfo) {
        this.transcodeTaskInfo = transcodeTaskInfo;
    }

}
