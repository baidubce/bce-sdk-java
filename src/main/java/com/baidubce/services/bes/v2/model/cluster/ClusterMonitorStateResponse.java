package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ClusterMonitorStateResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Object result;
}
