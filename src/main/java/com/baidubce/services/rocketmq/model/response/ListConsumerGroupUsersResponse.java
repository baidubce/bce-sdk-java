package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.AclUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListConsumerGroupUsersResponse extends RocketMQPagedResponse {
    private List<AclUser> users;
}
