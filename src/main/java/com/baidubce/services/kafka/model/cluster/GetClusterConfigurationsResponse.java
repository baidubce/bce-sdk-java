package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetClusterConfigurationsResponse extends AbstractBceResponse {

    private List<ClusterConfigOption> context;
}
