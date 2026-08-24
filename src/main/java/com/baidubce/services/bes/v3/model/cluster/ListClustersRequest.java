package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListClustersRequest extends AbstractBesV3Request {
    private Integer pageNo;
    private Integer pageSize;
    private String order;
    private String orderBy;
    private String payment;
    private String engine;
    private String status;
    private String clusterHealth;
    private String clusterName;
    private String clusterId;
    private String logicalZone;
    private String tagKey;
    private String tagValue;

}
