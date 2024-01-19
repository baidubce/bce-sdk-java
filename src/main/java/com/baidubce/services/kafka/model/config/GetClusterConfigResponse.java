package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetClusterConfigResponse extends AbstractBceResponse {

    private ClusterConfig config;
}
