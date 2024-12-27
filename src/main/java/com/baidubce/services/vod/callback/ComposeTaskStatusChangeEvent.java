package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.ComposeTaskInfo;

public class ComposeTaskStatusChangeEvent extends AbstractMediaProcessCompleteEvent {

    private ComposeTaskInfo composeTaskInfo;

    public ComposeTaskInfo getComposeTaskInfo() {
        return composeTaskInfo;
    }

    public void setComposeTaskInfo(ComposeTaskInfo composeTaskInfo) {
        this.composeTaskInfo = composeTaskInfo;
    }

}
