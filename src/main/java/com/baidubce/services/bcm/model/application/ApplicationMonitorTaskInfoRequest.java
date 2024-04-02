package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationMonitorTaskInfoRequest extends AbstractBceRequest {

    private long id;
    private String name;
    private String aliasName;
    private String appName;
    private String userId;
    private int cycle;
    private String target;
    private int type;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean hasAlarmConfig;

    /**
     *  以下是日志监控任务字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String logExample;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String matchRule;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer rate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LogExtractResult> extractResult;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Metric> metrics;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
