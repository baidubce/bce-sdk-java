package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class QueryConfigPriceRequest extends AbstractBesV3Request {
    private String payment;
    private Integer timeLength;
    private String timeUnit;
    private List<NodeSpec> nodeSpecs;
}
