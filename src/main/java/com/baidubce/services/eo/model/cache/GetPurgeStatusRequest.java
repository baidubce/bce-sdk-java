package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying cache purge task status.
 *
 */
public class GetPurgeStatusRequest extends EoRequest {
    /**
     * The site to query.
     */
    private String site;

    /**
     * The purge task id.
     */
    private String id;

    /**
     * The start of the query time range, UTC time. Defaults to 24h before endTime.
     */
    private String startTime;

    /**
     * The end of the query time range, UTC time. Defaults to now.
     */
    private String endTime;

    /**
     * The purge type to query.
     */
    private String type;

    /**
     * The nextMarker returned by the previous call, used as the start of this query.
     */
    private String marker;

    /**
     * @param site
     * @return this object
     */
    public GetPurgeStatusRequest withSite(String site) {
        this.site = site;
        return this;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @param id
     * @return this object
     */
    public GetPurgeStatusRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param startTime
     * @return this object
     */
    public GetPurgeStatusRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     * @return this object
     */
    public GetPurgeStatusRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @param type
     * @return this object
     */
    public GetPurgeStatusRequest withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param marker
     * @return this object
     */
    public GetPurgeStatusRequest withMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * @return marker
     */
    public String getMarker() {
        return marker;
    }

    /**
     * @param marker
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }
}
