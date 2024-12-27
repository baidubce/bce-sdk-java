package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.MediaRegulationTaskInfo;

public class MediaRegulationCompleteEvent extends AbstractMediaProcessCompleteEvent {

    private MediaRegulationTaskInfo regulationTaskInfo;

    public MediaRegulationTaskInfo getRegulationTaskInfo() {
        return regulationTaskInfo;
    }

    public void setRegulationTaskInfo(MediaRegulationTaskInfo regulationTaskInfo) {
        this.regulationTaskInfo = regulationTaskInfo;
    }

}