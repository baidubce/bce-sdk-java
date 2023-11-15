package com.baidubce.services.evs.model;

import java.util.Date;

public class DeviceStopRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -1466464559725004366L;

    private Date recoverTime;

    public Date getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(Date recoverTime) {
        this.recoverTime = recoverTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceStopRequest that = (DeviceStopRequest) o;

        return recoverTime != null ? recoverTime.equals(that.recoverTime) : that.recoverTime == null;
    }

    @Override
    public int hashCode() {
        return recoverTime != null ? recoverTime.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChannelStopRequest{" +
                "recoverTime=" + recoverTime +
                "} " + super.toString();
    }
}
