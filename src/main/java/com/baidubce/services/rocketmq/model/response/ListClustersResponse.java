package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Cluster;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClustersResponse extends RocketMQPagedResponse {
    private List<Cluster> clusters;
}
