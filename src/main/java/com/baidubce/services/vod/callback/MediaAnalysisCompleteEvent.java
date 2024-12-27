package com.baidubce.services.vod.callback;

import com.baidubce.services.vod.v2.model.MediaAnalysisTaskInfo;

public class MediaAnalysisCompleteEvent extends AbstractMediaProcessCompleteEvent {

    private MediaAnalysisTaskInfo analysisTaskInfo;

    public MediaAnalysisTaskInfo getAnalysisTaskInfo() {
        return analysisTaskInfo;
    }

    public void setAnalysisTaskInfo(MediaAnalysisTaskInfo analysisTaskInfo) {
        this.analysisTaskInfo = analysisTaskInfo;
    }

}
