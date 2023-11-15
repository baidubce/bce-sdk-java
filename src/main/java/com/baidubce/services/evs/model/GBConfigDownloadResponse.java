package com.baidubce.services.evs.model;

public class GBConfigDownloadResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 3903454530970957047L;

    private String serverIp;

    private Integer serverPort;

    private String sipType;

    private String streamType;

    private Integer configHeartbeatInterval;

    private Integer configRegisterExpire;

    private Integer configHeartbeatCount;

    private Long registerTime;

    private Long aliveTime;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getSipType() {
        return sipType;
    }

    public void setSipType(String sipType) {
        this.sipType = sipType;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public Integer getConfigHeartbeatInterval() {
        return configHeartbeatInterval;
    }

    public void setConfigHeartbeatInterval(Integer configHeartbeatInterval) {
        this.configHeartbeatInterval = configHeartbeatInterval;
    }

    public Integer getConfigRegisterExpire() {
        return configRegisterExpire;
    }

    public void setConfigRegisterExpire(Integer configRegisterExpire) {
        this.configRegisterExpire = configRegisterExpire;
    }

    public Integer getConfigHeartbeatCount() {
        return configHeartbeatCount;
    }

    public void setConfigHeartbeatCount(Integer configHeartbeatCount) {
        this.configHeartbeatCount = configHeartbeatCount;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public Long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(Long aliveTime) {
        this.aliveTime = aliveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GBConfigDownloadResponse that = (GBConfigDownloadResponse) o;

        if (serverIp != null ? !serverIp.equals(that.serverIp) : that.serverIp != null) {
            return false;
        }
        if (serverPort != null ? !serverPort.equals(that.serverPort) : that.serverPort != null) {
            return false;
        }
        if (sipType != null ? !sipType.equals(that.sipType) : that.sipType != null) {
            return false;
        }
        if (streamType != null ? !streamType.equals(that.streamType) : that.streamType != null) {
            return false;
        }
        if (configHeartbeatInterval != null ?
                !configHeartbeatInterval.equals(that.configHeartbeatInterval)
                : that.configHeartbeatInterval != null) {
            return false;
        }
        if (configRegisterExpire != null ?
                !configRegisterExpire.equals(that.configRegisterExpire) :
                that.configRegisterExpire != null) {
            return false;
        }
        if (configHeartbeatCount != null ?
                !configHeartbeatCount.equals(that.configHeartbeatCount) :
                that.configHeartbeatCount != null) {
            return false;
        }
        if (registerTime != null ? !registerTime.equals(that.registerTime) : that.registerTime != null) {
            return false;
        }
        return aliveTime != null ? aliveTime.equals(that.aliveTime) : that.aliveTime == null;
    }

    @Override
    public int hashCode() {
        int result = serverIp != null ? serverIp.hashCode() : 0;
        result = 31 * result + (serverPort != null ? serverPort.hashCode() : 0);
        result = 31 * result + (sipType != null ? sipType.hashCode() : 0);
        result = 31 * result + (streamType != null ? streamType.hashCode() : 0);
        result = 31 * result + (configHeartbeatInterval != null ? configHeartbeatInterval.hashCode() : 0);
        result = 31 * result + (configRegisterExpire != null ? configRegisterExpire.hashCode() : 0);
        result = 31 * result + (configHeartbeatCount != null ? configHeartbeatCount.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (aliveTime != null ? aliveTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GBConfigDownloadResponse{" +
                "serverIp='" + serverIp + '\'' +
                ", serverPort=" + serverPort +
                ", sipType='" + sipType + '\'' +
                ", streamType='" + streamType + '\'' +
                ", configHeartbeatInterval=" + configHeartbeatInterval +
                ", configRegisterExpire=" + configRegisterExpire +
                ", configHeartbeatCount=" + configHeartbeatCount +
                ", registerTime=" + registerTime +
                ", aliveTime=" + aliveTime +
                "} " + super.toString();
    }
}
