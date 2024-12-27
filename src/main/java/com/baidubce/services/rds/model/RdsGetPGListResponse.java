package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetPGListResponse extends AbstractBceResponse {
    private List<PgLogResponse> pgLogs;

    public List<PgLogResponse> getPgLogs() {
        return pgLogs;
    }

    public void setPgLogs(List<PgLogResponse> pgLogs) {
        this.pgLogs = pgLogs;
    }
}
