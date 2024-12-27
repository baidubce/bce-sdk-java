package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaRegulationOutputInfo {

    private String id;
    private String label;
    private List<MediaRegulationOutputTypeResult> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MediaRegulationOutputTypeResult> getResults() {
        return results;
    }

    public void setResults(List<MediaRegulationOutputTypeResult> results) {
        this.results = results;
    }

}
