package com.baidubce.services.bes.v3.model.action;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class GetOperationResponse extends AbstractBceResponse {
    private String actionId;
    private String operationId;
    private String clusterId;
    private Integer process;
    private List<GroupItem> groups;
    private String sourceContext;
    private String targetContext;
}
