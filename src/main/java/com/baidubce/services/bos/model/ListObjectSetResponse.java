package com.baidubce.services.bos.model;

import java.util.List;

/** Result of listing ObjectSets in a bucket. */
public class ListObjectSetResponse extends BosResponse {
    private Boolean isTruncated;
    private String nextMarker;
    private List<ObjectSetSummary> objectSets;

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public boolean isTruncated() {
        return Boolean.TRUE.equals(isTruncated);
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public List<ObjectSetSummary> getObjectSets() {
        return objectSets;
    }

    public void setObjectSets(List<ObjectSetSummary> objectSets) {
        this.objectSets = objectSets;
    }
}
