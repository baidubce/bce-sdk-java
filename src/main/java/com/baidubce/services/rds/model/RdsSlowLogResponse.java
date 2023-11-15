package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of rds slow log
 */
public class RdsSlowLogResponse extends AbstractBceResponse {

    private Integer count;

    private List<RdsSlowLogItem> slowLogs = new ArrayList<RdsSlowLogItem>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<RdsSlowLogItem> getSlowLogs() {
        return slowLogs;
    }

    public void setSlowLogs(List<RdsSlowLogItem> slowLogs) {
        this.slowLogs = slowLogs;
    }
}
