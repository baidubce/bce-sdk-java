package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListSchedulesRequest extends AbstractBesV3Request {
    private String clusterId;
    private Integer pageNo;
    private Integer pageSize;
}
