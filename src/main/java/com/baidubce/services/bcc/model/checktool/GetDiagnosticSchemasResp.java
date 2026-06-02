package com.baidubce.services.bcc.model.checktool;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetDiagnosticSchemasResp extends AbstractBceResponse {
    private List<MetricSet> metricSetList;

    @Data
    public static class MetricSet {

        private String metricSetId;

        private String metricSetName;

        private String description;

        private String supportText;
    }
}
