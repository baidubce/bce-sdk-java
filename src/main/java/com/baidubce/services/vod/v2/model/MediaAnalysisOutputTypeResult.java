package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaAnalysisOutputTypeResult {

    private String type;
    private List<MediaAnalysisOutputTypeResultItem> result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MediaAnalysisOutputTypeResultItem> getResult() {
        return result;
    }

    public void setResult(List<MediaAnalysisOutputTypeResultItem> result) {
        this.result = result;
    }

}
