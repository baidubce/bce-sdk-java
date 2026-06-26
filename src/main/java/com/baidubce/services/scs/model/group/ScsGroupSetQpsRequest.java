package com.baidubce.services.scs.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsGroupSetQpsRequest extends AbstractBceRequest {
    private String clusterShowId;
    private Integer qpsWrite;
    private Integer qpsRead;

    public String getClusterShowId() {
        return clusterShowId;
    }

    public void setClusterShowId(String clusterShowId) {
        this.clusterShowId = clusterShowId;
    }

    public Integer getQpsWrite() {
        return qpsWrite;
    }

    public void setQpsWrite(Integer qpsWrite) {
        this.qpsWrite = qpsWrite;
    }

    public Integer getQpsRead() {
        return qpsRead;
    }

    public void setQpsRead(Integer qpsRead) {
        this.qpsRead = qpsRead;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
