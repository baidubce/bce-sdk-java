package com.baidubce.services.eo.model.logmodel;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

public class GetDomainListLogResponse extends EoResponse {
    private int totalCount;
    private List<LogEntry> logEntryList;

    /**
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return logEntryList
     */
    public List<LogEntry> getLogEntryList() {
        return logEntryList;
    }

    /**
     *
     * @param logEntryList
     */
    public void setLogEntryList(List<LogEntry> logEntryList) {
        this.logEntryList = logEntryList;
    }
}
