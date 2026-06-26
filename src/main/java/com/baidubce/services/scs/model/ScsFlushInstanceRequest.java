package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to flush scs instance
 */
public class ScsFlushInstanceRequest extends AbstractBceRequest {

    private String instanceId;
    private String password;
    private Integer dbIndex;
    private Boolean isFlushExpired;
    private Boolean isDefer;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    public Boolean getIsFlushExpired() {
        return isFlushExpired;
    }

    public void setIsFlushExpired(Boolean flushExpired) {
        isFlushExpired = flushExpired;
    }

    public Boolean getIsDefer() {
        return isDefer;
    }

    public void setIsDefer(Boolean defer) {
        isDefer = defer;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
