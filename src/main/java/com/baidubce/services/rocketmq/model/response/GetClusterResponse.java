package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.ClusterDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetClusterResponse extends RocketMQBaseResponse {
    private ClusterDetail cluster;
}
