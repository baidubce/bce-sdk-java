package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSlowLogGetErrorLogDetailsResponse extends AbstractBceResponse {
    private Integer count;
    private List<LogErrorDetail> errorLogs;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<LogErrorDetail> getErrorLogs() {
        return errorLogs;
    }

    public void setErrorLogs(List<LogErrorDetail> errorLogs) {
        this.errorLogs = errorLogs;
    }
}
