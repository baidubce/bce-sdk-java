package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetClusterAccessEndpointsResponse extends AbstractBceResponse {

    private List<AccessEndpoint> accessEndpoints;
}
