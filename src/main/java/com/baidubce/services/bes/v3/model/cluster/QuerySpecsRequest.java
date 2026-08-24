package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class QuerySpecsRequest extends AbstractBesV3Request {
    private String engine;
    private String engineType;
    private String version;
    private List<String> features;
    private List<String> logicalZones;
}
