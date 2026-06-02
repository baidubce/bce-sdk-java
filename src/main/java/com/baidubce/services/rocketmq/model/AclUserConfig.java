package com.baidubce.services.rocketmq.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class AclUserConfig {
    private String name;
    private Set<String> whiteRemoteAddress;
    private Boolean admin;
    private String defaultTopicPerm;
    private String defaultGroupPerm;
    private Set<AclPolicyItem> topicPerms;
    private Set<AclPolicyItem> groupPerms;
}
