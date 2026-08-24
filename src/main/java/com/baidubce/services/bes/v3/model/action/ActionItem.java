package com.baidubce.services.bes.v3.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionItem {
    private String clusterId;
    private String clusterName;
    private String name;
    private String nameCN;
    private String actionId;
    private String status;
    private String createTime;
    private String endTime;
    private String runningTime;
}
