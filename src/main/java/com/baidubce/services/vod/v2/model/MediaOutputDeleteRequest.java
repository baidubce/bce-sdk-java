package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import java.util.List;

public class MediaOutputDeleteRequest extends AbstractBceRequest {

    private List<String> transcodeOutputIds;
    private List<String> thumbnailOutputIds;
    private List<String> subtitleOutputIds;
    private List<String> analysisOutputIds;
    private List<String> regulationOutputIds;

    public List<String> getTranscodeOutputIds() {
        return transcodeOutputIds;
    }

    public void setTranscodeOutputIds(List<String> transcodeOutputIds) {
        this.transcodeOutputIds = transcodeOutputIds;
    }

    public List<String> getThumbnailOutputIds() {
        return thumbnailOutputIds;
    }

    public void setThumbnailOutputIds(List<String> thumbnailOutputIds) {
        this.thumbnailOutputIds = thumbnailOutputIds;
    }

    public List<String> getSubtitleOutputIds() {
        return subtitleOutputIds;
    }

    public void setSubtitleOutputIds(List<String> subtitleOutputIds) {
        this.subtitleOutputIds = subtitleOutputIds;
    }

    public List<String> getAnalysisOutputIds() {
        return analysisOutputIds;
    }

    public void setAnalysisOutputIds(List<String> analysisOutputIds) {
        this.analysisOutputIds = analysisOutputIds;
    }

    public List<String> getRegulationOutputIds() {
        return regulationOutputIds;
    }

    public void setRegulationOutputIds(List<String> regulationOutputIds) {
        this.regulationOutputIds = regulationOutputIds;
    }


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaOutputDeleteRequest withTranscodeOutputIds(List<String> transcodeOutputIds) {
        this.transcodeOutputIds = transcodeOutputIds;
        return this;
    }

    public MediaOutputDeleteRequest withThumbnailOutputIds(List<String> thumbnailOutputIds) {
        this.thumbnailOutputIds = thumbnailOutputIds;
        return this;
    }

    public MediaOutputDeleteRequest withSubtitleOutputIds(List<String> subtitleOutputIds) {
        this.subtitleOutputIds = subtitleOutputIds;
        return this;
    }

    public MediaOutputDeleteRequest withAnalysisOutputIds(List<String> analysisOutputIds) {
        this.analysisOutputIds = analysisOutputIds;
        return this;
    }

    public MediaOutputDeleteRequest withRegulationOutputIds(List<String> regulationOutputIds) {
        this.regulationOutputIds = regulationOutputIds;
        return this;
    }

}
