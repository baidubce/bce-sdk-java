package com.baidubce.services.cdn.model.stat;


import com.baidubce.services.cdn.model.CdnResponse;

import java.util.ArrayList;
import java.util.List;

public class GetXcdnStatMetricResponse extends CdnResponse {
    private String status;

    private List<XcdnDetail> details = new ArrayList<XcdnDetail>();

    private Long count;

    private Summary summary;

    private DayInfo days;

    public GetXcdnStatMetricResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<XcdnDetail> getDetails() {
        return details;
    }

    public void setDetails(List<XcdnDetail> details) {
        this.details = details;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public DayInfo getDays() {
        return days;
    }

    public void setDays(DayInfo days) {
        this.days = days;
    }
}
