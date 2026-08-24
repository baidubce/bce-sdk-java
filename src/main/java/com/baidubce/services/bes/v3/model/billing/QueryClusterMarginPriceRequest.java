package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class QueryClusterMarginPriceRequest extends AbstractBesV3Request {
    private String clusterId;
    private Integer resizeType;
    private BillingConfig billingConfig;
    private List<String> logicalZones;
    private String subnetId;
    private Boolean enableDeploySet;
    private List<NodeSpec> nodeSpecs;
    private String mode;
}
