package com.baidubce.services.bcc.model.userop;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class DescribeServerEventRecordReq extends ListRequest {

    /**
     * List of server event Ids
     */
    private List<String> serverEventIds;

    /**
     * List of instance Ids
     */
    private List<String> instanceIds;

    /**
     * Fault instance product type
     */
    private String productCategory;

    /**
     * Event type
     */
    private String serverEventType;

    /**
     * Time log type
     */
    private String serverEventLogTimeFilter;

    /**
     * Start Time, format is yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date periodStartTime;

    /**
     * End Time, format is yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date periodEndTime;

    public void setServerEventIds(List<String> serverEventIds) {
        this.serverEventIds = serverEventIds;
    }

    public List<String> getServerEventIds() {
        return this.serverEventIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    public List<String> getInstanceIds() {
        return this.instanceIds;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setServerEventType(String serverEventType) {
        this.serverEventType = serverEventType;
    }

    public String getServerEventType() {
        return this.serverEventType;
    }

    public void setServerEventLogTimeFilter(String serverEventLogTimeFilter) {
        this.serverEventLogTimeFilter = serverEventLogTimeFilter;
    }

    public String getServerEventLogTimeFilter() {
        return this.serverEventLogTimeFilter;
    }

    public void setPeriodStartTime(Date periodStartTime) {
        this.periodStartTime = periodStartTime;
    }

    public Date getPeriodStartTime() {
        return this.periodStartTime;
    }

    public void setPeriodEndTime(Date periodEndTime) {
        this.periodEndTime = periodEndTime;
    }

    public Date getPeriodEndTime() {
        return this.periodEndTime;
    }

    @Override
    public String toString() {
        return "DescribeServerEventRecordV3Req{"
                + "serverEventIds=" + serverEventIds + "\n"
                + "instanceIds=" + instanceIds + "\n"
                + "productCategory=" + productCategory + "\n"
                + "serverEventType=" + serverEventType + "\n"
                + "serverEventLogTimeFilter=" + serverEventLogTimeFilter + "\n"
                + "periodStartTime=" + periodStartTime + "\n"
                + "periodEndTime=" + periodEndTime + "\n"
                + "}";
    }

    @Override
    public DescribeServerEventRecordReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}