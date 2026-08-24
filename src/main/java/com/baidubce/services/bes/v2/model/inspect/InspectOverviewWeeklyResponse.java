package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for the weekly inspection overview.
 */
@Data
public class InspectOverviewWeeklyResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private Integer count;
        private List<RiskItem> riskItems;
    }

    @Data
    public static class RiskItem {
        private String id;
        private Integer highRiskCount;
        private Integer lowRiskCount;
    }
}
