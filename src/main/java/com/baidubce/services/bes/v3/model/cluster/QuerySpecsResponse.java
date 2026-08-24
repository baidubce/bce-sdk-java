package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class QuerySpecsResponse extends AbstractBceResponse {
    private List<NodeAvailableSpec> nodeAvailableSpecs;
    private List<QueryNodeSpec> nodeSpecs;
    private List<DiskSpecInfo> diskSpecs;
}
