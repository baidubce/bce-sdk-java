package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

/**
 * The response of querying cache prefetch task status.
 */
public class GetPrefetchStatusResponse extends EoResponse {
    /**
     * The prefetch task details.
     */
    private List<PrefetchStatus> details;

    /**
     * true means there are more records after this query range, false means it is the last page.
     */
    private Boolean isTruncated;

    /**
     * The marker to pass in the next call to get subsequent records.
     */
    private String nextMarker;

    /**
     * @return details
     */
    public List<PrefetchStatus> getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(List<PrefetchStatus> details) {
        this.details = details;
    }

    /**
     * @return isTruncated
     */
    public Boolean getIsTruncated() {
        return isTruncated;
    }

    /**
     * @param isTruncated
     */
    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    /**
     * @return nextMarker
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * @param nextMarker
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }
}
