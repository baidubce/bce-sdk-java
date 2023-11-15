package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class LimitBandwidth extends JsonObject {

    /**
     * 开启/关闭带宽封顶配置
     * 必选
     */
    private boolean enabled;

    /**
     * 带宽封顶阈值，带单位G，如"1G", "200G"。enable为true时必选
     * 必选
     */
    private String threshold;

    /**
     * 带宽达到阈值时触发的动作，值为deny/stop，"stop"表示stop加速域名，"deny"表示拒绝所有请求。当enabled为true时必选
     * 必选
     */
    private String action;

    /**
     * 主动设置是否执行action，
     * 当设置为true时，无论带宽是否达到阈值，当前周期都会触发action，
     * 当设置为false时，无论带宽是否达到阈值，当前周期都不会触发action
     * 必选
     */
    private boolean actived;

    public LimitBandwidth() {
    }

    public LimitBandwidth withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public LimitBandwidth withActived(boolean actived) {
        this.actived = actived;
        return this;
    }

    public LimitBandwidth withThreshold(String threshold) {
        this.threshold = threshold;
        return this;
    }

    public LimitBandwidth withAction(String action) {
        this.action = action;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }
}
