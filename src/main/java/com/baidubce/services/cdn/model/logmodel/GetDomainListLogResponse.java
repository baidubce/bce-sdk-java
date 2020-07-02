package com.baidubce.services.cdn.model.logmodel;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainListLogResponse extends CdnResponse {
    private String startTime;
    private String endTime;
    private List<LogEntry> urls;
    private int totalCount;

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
     * @return urls
     */
    public List<LogEntry> getUrls() {
        return urls;
    }

    /**
     * @param urls logmodel url list
     */
    public void setUrls(List<LogEntry> urls) {
        this.urls = urls;
    }

    /**
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount url list count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
