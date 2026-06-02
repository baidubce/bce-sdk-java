package com.baidubce.services.rocketmq.model.request;

import com.baidubce.services.rocketmq.model.AclPolicyItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateAclUserConfigRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String username;
    private String password;
    private Set<String> whiteRemoteAddress;
    private Boolean admin;
    private String defaultTopicPerm;
    private String defaultGroupPerm;
    private Set<AclPolicyItem> topicPerms;
    private Set<AclPolicyItem> groupPerms;
}
