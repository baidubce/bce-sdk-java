package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

import java.util.List;

@Data
public class CreateClusterRequest extends AbstractBesV3Request {
    private String payment;
    private Object paymentInfo;
    private List<String> couponIds;
    private String name;
    private String engine;
    private String engineType;
    private String version;
    private String adminPassword;
    private Boolean enableHttps;
    private Boolean deletionProtection;
    private List<String> logicalZones;
    private String vpcId;
    private String subnetId;
    private Boolean enableDeploySet;
    private List<NodeSpec> nodeSpecs;
    private List<Tag> tags;
}
