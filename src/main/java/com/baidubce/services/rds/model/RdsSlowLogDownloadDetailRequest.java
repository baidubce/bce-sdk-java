package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The detail info of downloading rds slow log
 */
public class RdsSlowLogDownloadDetailRequest extends AbstractBceRequest {

    private String instanceId;
    private String logId;
    private Integer downloadValidTimeInSec = 30 * 60;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Integer getDownloadValidTimeInSec() {
        return downloadValidTimeInSec;
    }

    public void setDownloadValidTimeInSec(Integer downloadValidTimeInSec) {
        this.downloadValidTimeInSec = downloadValidTimeInSec;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
