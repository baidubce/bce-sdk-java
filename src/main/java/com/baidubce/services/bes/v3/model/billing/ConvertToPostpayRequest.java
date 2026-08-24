package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ConvertToPostpayRequest extends AbstractBesV3Request {
    private String clusterId;
}
