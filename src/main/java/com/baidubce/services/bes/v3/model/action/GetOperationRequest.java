package com.baidubce.services.bes.v3.model.action;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class GetOperationRequest extends AbstractBesV3Request {
    private String clusterId;
    private String actionId;
    private String operationId;
}
