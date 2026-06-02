package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsMajorVersionPrecheckResponse extends AbstractBceResponse {
    private String tdeStatus;
    private String sysdb;
    private String gtid;
    private String noninnodb;
    private String fts;
    private String event;
    private String trigger;


    public String getTdeStatus() {
        return tdeStatus;
    }

    public void setTdeStatus(String tdeStatus) {
        this.tdeStatus = tdeStatus;
    }

    public String getSysdb() {
        return sysdb;
    }

    public void setSysdb(String sysdb) {
        this.sysdb = sysdb;
    }

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid;
    }

    public String getNoninnodb() {
        return noninnodb;
    }

    public void setNoninnodb(String noninnodb) {
        this.noninnodb = noninnodb;
    }

    public String getFts() {
        return fts;
    }

    public void setFts(String fts) {
        this.fts = fts;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
