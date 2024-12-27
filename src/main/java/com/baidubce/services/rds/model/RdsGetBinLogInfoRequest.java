package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetBinLogInfoRequest extends AbstractBceRequest {
    private String instanceId;
    private String logId;
    private String downloadValidTimeInSec;

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

    public String getDownloadValidTimeInSec() {
        return downloadValidTimeInSec;
    }

    public void setDownloadValidTimeInSec(String downloadValidTimeInSec) {
        this.downloadValidTimeInSec = downloadValidTimeInSec;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
