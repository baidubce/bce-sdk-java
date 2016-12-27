package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/19.
 */
public class ListStreamStatisticsResponse extends AbstractBceResponse {

    private List<LiveStreamStatistics> streamStatisticsList = null;

    private Integer totalCount = null;

    public List<LiveStreamStatistics> getStreamStatisticsList() {
        return streamStatisticsList;
    }

    public void setStreamStatisticsList(List<LiveStreamStatistics> streamStatisticsList) {
        this.streamStatisticsList = streamStatisticsList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListStreamStatisticsResponse {\n");
        sb.append("    streamStatisticsList: ").append(streamStatisticsList).append("\n");
        sb.append("    totalCount: ").append(totalCount).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
