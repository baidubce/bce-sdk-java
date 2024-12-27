package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaAnalysisOutputInfo {

    private String id;
    private List<MediaAnalysisOutputTypeResult> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MediaAnalysisOutputTypeResult> getResults() {
        return results;
    }

    public void setResults(List<MediaAnalysisOutputTypeResult> results) {
        this.results = results;
    }

}
