package com.baidubce.services.vca.model;

import java.util.Date;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class ImageAnalyzeResponse extends AbstractBceResponse {
    private String source;
    private String title;
    private String preset;
    private String status;
    private Date publishTime; // finish time
    private String error; // exists only when status = ERROR
    private List<ImageAnalyzeResult> results; // exists only when status = FINISHED

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImageAnalyzeResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", preset='").append(preset).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", finishTime='").append(publishTime).append('\'');
        sb.append(", results='").append(results).append('\'');
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ImageAnalyzeResult> getResults() {
        return results;
    }

    public void setResults(List<ImageAnalyzeResult> results) {
        this.results = results;
    }
}
