package com.baidubce.services.scs.model.instance;

import java.util.List;

/**
 * The maintain time of scs instance.
 */
public class ScsMaintainTime {

    private String startTime;
    private Integer duration;
    private List<Integer> period;

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
}
