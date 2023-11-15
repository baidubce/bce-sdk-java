package com.baidubce.services.as.model.asgroup;

public class HealthCheckConfig {
    private int healthCheckInterval;
    private int graceTime;

    public int getHealthCheckInterval() {
        return healthCheckInterval;
    }

    public void setHealthCheckInterval(int healthCheckInterval) {
        this.healthCheckInterval = healthCheckInterval;
    }

    public int getGraceTime() {
        return graceTime;
    }

    public void setGraceTime(int graceTime) {
        this.graceTime = graceTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HeathCheckConfig{");
        sb.append("healthCheckInterval=").append(healthCheckInterval);
        sb.append(", graceTime=").append(graceTime);
        sb.append('}');
        return sb.toString();
    }
}
