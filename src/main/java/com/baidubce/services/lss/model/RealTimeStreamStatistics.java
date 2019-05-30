package com.baidubce.services.lss.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhengfeng on 17/10/17.
 */
public class RealTimeStreamStatistics implements Serializable {

    private String stream;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'",
            timezone = "GMT+8"
    )
    private Date date;

    private Long playCount;

    private Long bandwidthInBps;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getBandwidthInBps() {
        return bandwidthInBps;
    }

    public void setBandwidthInBps(Long bandwidthInBps) {
        this.bandwidthInBps = bandwidthInBps;
    }

    @Override
    public String toString() {
        return "RealTimeStreamStatistics{"
                + "stream='" + stream + '\''
                + ", date=" + date
                + ", playCount=" + playCount
                + ", bandwidthInBps=" + bandwidthInBps
                + '}';
    }

}
