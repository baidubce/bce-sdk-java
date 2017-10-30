package com.baidubce.services.iothub.model;

import org.joda.time.LocalDate;

public class QueryEndpointDailyMqttUsageRequest extends BaseRequest {

    private LocalDate start;

    private LocalDate end;

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
