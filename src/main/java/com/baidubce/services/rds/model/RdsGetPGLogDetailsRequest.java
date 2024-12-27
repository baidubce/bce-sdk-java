package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetPGLogDetailsRequest extends AbstractBceRequest {
    private String instanceId;
    private String pglogId;
    private Integer downloadValidTimeInSec;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getPglogId() {
        return pglogId;
    }

    public void setPglogId(String pglogId) {
        this.pglogId = pglogId;
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
