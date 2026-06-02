package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.AclUserConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClusterAclUserConfigsResponse extends RocketMQPagedResponse {
    private List<AclUserConfig> users;
}
