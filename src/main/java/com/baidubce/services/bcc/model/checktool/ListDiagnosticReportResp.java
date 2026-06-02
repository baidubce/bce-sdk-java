package com.baidubce.services.bcc.model.checktool;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ListDiagnosticReportResp extends AbstractBceResponse {

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 标记查询的起始位置
     */
    private String marker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    private List<ExecutionRecordUO> diagnosticReports;

    @Data
    public static class ExecutionRecordUO {
        private String reportId;

        private String instanceType;

        private String instanceId;

        private String metricSetId;

        private LocalDateTime createdTime;

        private String status;

        private String result;

        private List<IssueUO> issues;

        @Data
        public static class IssueUO {
            private String metricCategory;

            private String metricItem;

            private String severityResult;

            private String advice;
        }
    }

}
