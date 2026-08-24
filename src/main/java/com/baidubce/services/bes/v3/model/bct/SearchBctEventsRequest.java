package com.baidubce.services.bes.v3.model.bct;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class SearchBctEventsRequest extends AbstractBesV3Request {
    private String startTime;
    private String endTime;
    private Integer pageSize;
    private String domainId;
    private String nextMarker;
    private List<BctQueryFilter> filters;

    @Data
    public static class BctQueryFilter {
        private String field;
        private String value;
    }
}
