package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/19.
 */
public class GetStreamStatisticsRequest extends AbstractBceRequest {

    private String domain = null;

    private String app = null;

    private String stream = null;

    private String startDate = null;

    private String endDate = null;

    private Boolean aggregate = null;

    public GetStreamStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GetStreamStatisticsRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public GetStreamStatisticsRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public GetStreamStatisticsRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public GetStreamStatisticsRequest withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public GetStreamStatisticsRequest withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Boolean getAggregate() {
        return aggregate;
    }

    public void set(Boolean aggregate) {
        this.aggregate = aggregate;
    }

    public GetStreamStatisticsRequest withAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetStreamStatisticsRequest {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    stream: ").append(stream).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
