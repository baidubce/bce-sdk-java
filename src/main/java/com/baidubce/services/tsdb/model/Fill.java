package com.baidubce.services.tsdb.model;

/**
 * Represent the fill in query.
 *
 * @author linpengxiang
 */
public class Fill {

    /**
     * Required.
     * The type of interpolation which can be "Linear" or "Previous".
     */
    private String type;

    /**
     * Required.
     * The interval for interpolation. eg: "1 minute" or "5 hours".
     * It will Interpolate a value when there are not value in the interval.
     */
    private String interval;

    /**
     * Optional.
     * The maximum write interval for data points. eg: "1 minute", or "5 hours".
     * It will consider data points in [start - maxWriteInterval, end + maxWriteInterval] for interpolation.
     */
    private String maxWriteInterval;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getMaxWriteInterval() {
        return maxWriteInterval;
    }

    public void setMaxWriteInterval(String maxWriteInterval) {
        this.maxWriteInterval = maxWriteInterval;
    }

    public Fill withType(String type) {
        setType(type);
        return this;
    }

    public Fill withInterval(String interval) {
        setInterval(interval);
        return this;
    }

    public Fill withMaxWriteInterval(String maxWriteInterval) {
        setMaxWriteInterval(maxWriteInterval);
        return this;
    }

}
