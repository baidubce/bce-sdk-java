package com.baidubce.services.bes.v3.model.action;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListOperationsResponse extends AbstractBceResponse {
    private String clusterId;
    private String actionId;
    private String name;
    private String nameCN;
    private String status;
    private List<OperationItem> operations;
}
