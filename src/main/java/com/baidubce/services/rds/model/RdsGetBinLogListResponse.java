package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetBinLogListResponse extends AbstractBceResponse {
    private List<RdsBinlogs> binlogs;

    public List<RdsBinlogs> getBinlogs() {
        return binlogs;
    }

    public void setBinlogs(List<RdsBinlogs> binlogs) {
        this.binlogs = binlogs;
    }
}
