package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Action;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClusterActionsResponse extends RocketMQPagedResponse {
    private List<Action> actions;
}
