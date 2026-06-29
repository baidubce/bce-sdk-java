package com.baidubce.services.mongodb.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Marker-based paging result, matching the server-side {@code LogicMarkerResultResponse<T>}.
 * The data list is carried by the {@code result} field.
 *
 * @param <T> element type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbMarkerResultResponse<T> extends AbstractBceResponse {

    private List<T> result;

    private String marker;

    private Integer maxKeys;

    private Boolean isTruncated;

    private String nextMarker;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }
}
