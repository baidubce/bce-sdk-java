package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/14.
 */
public class ListStreamResponse extends AbstractBceResponse {

    private List<LiveStream> streams = null;

    private String marker;

    private Boolean isTruncated;

    private String nextMarker;

    public List<LiveStream> getStreams() {
        return streams;
    }

    public void setStreams(List<LiveStream> streams) {
        this.streams = streams;
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

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListStreamResponse {\n");
        sb.append("    streams: ").append(streams).append("\n");
        sb.append("    marker: ").append(marker).append("\n");
        sb.append("    nextMarker: ").append(nextMarker).append("\n");
        sb.append("    isTruncated: ").append(isTruncated).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
