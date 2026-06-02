package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.AclUserConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAclUserConfigResponse extends RocketMQBaseResponse {
    private AclUserConfig user;
}
