package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/19.
 */
public class GetStreamStatisticsResponse extends AbstractBceResponse {

    private String app = null;

    private String stream = null;

    private String startDate = null;

    private String endDate = null;

    private List<LiveStatisticsWithDate> statistics = null;

    private LiveStatistics aggregate = null;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<LiveStatisticsWithDate> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<LiveStatisticsWithDate> statistics) {
        this.statistics = statistics;
    }

    public LiveStatistics getAggregate() {
        return aggregate;
    }

    public void setAggregate(LiveStatistics aggregate) {
        this.aggregate = aggregate;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetStreamStatisticsRespons {\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    stream: ").append(stream).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    statistics: ").append(statistics).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
