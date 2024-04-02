package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationMonitorTaskResponse extends AbstractBceResponse {
    private long id;
    private String name;
    private String aliasName;
    private String appName;
    private String userId;
    private int cycle;
    private String target;
    private int type;
    private String description;

    private boolean hasAlarmConfig;

    private String logExample;
    private String matchRule;
    private Integer rate;
    private List<LogExtractResult> extractResult;
    private List<Metric> metrics;

}
