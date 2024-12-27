package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.MediaFetchTaskInfo;

public class MediaFetchCompleteEvent extends AbstractMediaProcessCompleteEvent {

    private MediaFetchTaskInfo fetchTaskInfo;

    public MediaFetchTaskInfo getFetchTaskInfo() {
        return fetchTaskInfo;
    }

    public void setFetchTaskInfo(MediaFetchTaskInfo fetchTaskInfo) {
        this.fetchTaskInfo = fetchTaskInfo;
    }

}
