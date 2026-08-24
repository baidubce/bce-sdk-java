package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class GetNodeListResponse extends AbstractBceResponse {
    private List<ClusterDetailNodeSpec> nodeSpecs;
    private List<ClusterNode> nodes;
}
