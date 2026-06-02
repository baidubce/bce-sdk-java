package com.baidubce.services.rocketmq.model.request;

import com.baidubce.services.rocketmq.model.ClusterType;
import com.baidubce.services.rocketmq.model.ProvisionedCluster;
import com.baidubce.services.rocketmq.model.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateClusterRequest extends RocketMQBaseRequest {
    private String name;
    private ClusterType type;
    private ProvisionedCluster provisioned;
    private List<Tag> tags;
}
