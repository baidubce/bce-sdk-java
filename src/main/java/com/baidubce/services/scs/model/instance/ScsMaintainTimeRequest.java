package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ScsMaintainTimeRequest extends AbstractBceRequest {
    private String instanceId;
    private String startTime;
    private Integer duration;
    private List<Integer> period;

    @JsonIgnore
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Integer> getPeriod() {
        return period;
    }

    public void setPeriod(List<Integer> period) {
        this.period = period;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
