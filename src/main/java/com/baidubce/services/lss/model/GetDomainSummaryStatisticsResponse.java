package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by wuyafei on 16/10/17.
 */
public class GetDomainSummaryStatisticsResponse extends AbstractBceResponse {

    private String startTiem = null;

    private String endTime = null;

    private LiveStatisticsSummary summary = null;

    public String getStartTiem() {
        return startTiem;
    }

    public void setStartTime(String startTiem) {
        this.startTiem = startTiem;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LiveStatisticsSummary getSummary() {
        return summary;
    }

    public void setSummary(LiveStatisticsSummary summary) {
        this.summary = summary;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetDomainSUmmaryStatisticsResponse {\n");
        sb.append("    startTime: ").append(startTiem).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    summary: ").append(summary).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
