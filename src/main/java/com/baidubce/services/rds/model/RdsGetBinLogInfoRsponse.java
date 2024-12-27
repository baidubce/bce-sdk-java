package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetBinLogInfoRsponse extends AbstractBceResponse {
    private RdsBinLog binlog;

    public RdsBinLog getBinlog() {
        return binlog;
    }

    public void setBinlog(RdsBinLog binlog) {
        this.binlog = binlog;
    }
}
