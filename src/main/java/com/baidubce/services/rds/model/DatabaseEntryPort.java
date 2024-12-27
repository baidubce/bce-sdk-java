package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseEntryPort extends AbstractBceRequest {
    private Integer entryPort;

    public Integer getEntryPort() {
        return entryPort;
    }

    public void setEntryPort(Integer entryPort) {
        this.entryPort = entryPort;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
