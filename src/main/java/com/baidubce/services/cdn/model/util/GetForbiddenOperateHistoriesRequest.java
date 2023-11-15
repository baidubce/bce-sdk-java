package com.baidubce.services.cdn.model.util;


public class GetForbiddenOperateHistoriesRequest extends GetForbiddenUrlsRequest {

    /**
     * 指定时间范围查询，开始时间，距当前时间点不能超过90天，北京时间
     * 可选
     */
    private String startTime;

    /**
     * 指定时间范围查询，截止时间，不能大于当前时间，北京时间
     * 可选
     */
    private String endTime;

    public GetForbiddenOperateHistoriesRequest() {
    }

    public GetForbiddenOperateHistoriesRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public GetForbiddenOperateHistoriesRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
