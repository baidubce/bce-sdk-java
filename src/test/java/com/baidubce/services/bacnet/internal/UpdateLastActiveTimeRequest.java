package com.baidubce.services.bacnet.internal;

import com.baidubce.model.GenericAccountRequest;

import java.util.Date;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class UpdateLastActiveTimeRequest extends GenericAccountRequest {
    private Date lastActiveTime;

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}
