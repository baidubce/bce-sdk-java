package com.baidubce.services.vod.v2.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Collection;
import java.util.LinkedList;

public class ListByMarkerResponse<T> extends AbstractBceResponse {

    private Collection<T> data = new LinkedList<>();
    private boolean isTruncated = false;
    private String marker;
    private String nextMarker;

    public static <T> ListByMarkerResponse<T> of(ListByMarkerResponse<?> response, Collection<T> data) {
        ListByMarkerResponse<T> newResponse = new ListByMarkerResponse<>();
        newResponse.setTruncated(response.isTruncated());
        newResponse.setMarker(response.getMarker());
        newResponse.setNextMarker(response.getNextMarker());
        newResponse.setData(data);
        return newResponse;
    }

    public static <T> ListByMarkerResponse<T> of(boolean isTruncated, String marker, String nextMarker, Collection<T> data) {
        ListByMarkerResponse<T> response = new ListByMarkerResponse<>();
        response.setTruncated(isTruncated);
        response.setMarker(marker);
        response.setNextMarker(nextMarker);
        response.setData(data);
        return response;
    }

    public static <T> ListByMarkerResponse<T> of(Collection<T> data) {
        ListByMarkerResponse<T> newResponse = new ListByMarkerResponse<>();
        newResponse.setData(data);
        return newResponse;
    }

    public static <T> ListByMarkerResponse<T> empty() {
        return new ListByMarkerResponse<>();
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isTruncated() {
        return isTruncated;
    }

    public void setTruncated(boolean truncated) {
        isTruncated = truncated;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public ListByMarkerResponse() {
    }
}