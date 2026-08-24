package com.baidubce.services.bes.v3.model.action;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListActionsRequest extends AbstractBesV3Request {
    private Integer pageNo;
    private Integer pageSize;
    private String name;
    private String status;
    private String orderBy;
    private String order;
    private String clusterName;
    private String clusterId;
}
