package com.baidubce.services.cdn.model.cache;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.Date;

/**
 * create by changxing01 on 19/8/28
 */
public class GetCacheDetailRequest extends CdnRequest {
    private String marker;
    private String type;
    private String url;

    private Date endTime;
    private Date startTime;

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
     * @return returns this object
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param t
     */
    public void setEndTime(Date t) {
        this.endTime = t;
    }

    /**
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param t
     */
    public void setStartTime(Date t) {
        this.startTime = t;
    }

    /**
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param type
     * @return returns this object
     */
    public GetCacheDetailRequest withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * @param url
     * @return returns this object
     */
    public GetCacheDetailRequest withUrl(String url) {
        this.setUrl(url);
        return this;
    }

    /**
     * @param startTime
     * @return returns this object
     */
    public GetCacheDetailRequest withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    /**
     * @param endTime
     * @return returns this object
     */
    public GetCacheDetailRequest withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }
}
