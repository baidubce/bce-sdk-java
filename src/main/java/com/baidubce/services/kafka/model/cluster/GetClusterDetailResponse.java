package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetClusterDetailResponse extends AbstractBceResponse {

    private ClusterDetail cluster;
}
