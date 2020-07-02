package com.baidubce.services.cdn.model.cache;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetCacheRecordsResponse extends CdnResponse {
    private List<RecordStatus> details;
    private boolean isTruncated;
    private String nextMarker;

    /**
     * @return details
     */
    public List<RecordStatus> getDetails() {
        return details;
    }

    /**
     * @param details detail of task
     */
    public void setDetails(List<RecordStatus> details) {
        this.details = details;
    }

    /**
     * @return isTruncated
     */
    public boolean isTruncated() {
        return isTruncated;
    }

    /**
     * @param isTruncated
     */
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * @return nextMarker
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * @param nextMarker Subsequent list tag
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }
}
