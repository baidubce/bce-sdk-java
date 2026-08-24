package com.baidubce.services.bes.v3.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationItem {
    private String actionId;
    private String operationId;
    private String type;
    private String typeCN;
    private String state;
    private Integer process;
    private String startTime;
    private String endTime;
    private String runningTime;
}
