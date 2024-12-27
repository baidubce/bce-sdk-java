package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowLogGetErrorLogListResponse extends AbstractBceResponse {
    private List<OpenapiErrorlog> errorlogs;

    public List<OpenapiErrorlog> getErrorlogs() {
        return errorlogs;
    }

    public void setErrorlogs(List<OpenapiErrorlog> errorlogs) {
        this.errorlogs = errorlogs;
    }
}
