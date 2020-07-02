package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class UpdateSchedulePlanRequest extends AbstractBceRequest {
    private String schedulePlanId; // 定时任务id

    private List<StepConfig> steps; // 定时任务步骤

    private Schedule schedule; // 定时任务调度策略

    public UpdateSchedulePlanRequest withSchedulePlanId(String schedulePlanId) {
        this.setSchedulePlanId(schedulePlanId);
        return this;
    }

    public UpdateSchedulePlanRequest withSteps(List<StepConfig> steps) {
        this.setSteps(steps);
        return this;
    }

    public UpdateSchedulePlanRequest withSchedule(Schedule schedule) {
        this.setSchedule(schedule);
        return this;
    }

    public String getSchedulePlanId() {
        return schedulePlanId;
    }

    public void setSchedulePlanId(String schedulePlanId) {
        this.schedulePlanId = schedulePlanId;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }

    public void setSteps(List<StepConfig> steps) {
        this.steps = steps;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
