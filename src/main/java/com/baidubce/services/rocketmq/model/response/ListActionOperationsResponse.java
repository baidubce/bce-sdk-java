package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Operation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListActionOperationsResponse extends RocketMQPagedResponse {
    private List<Operation> operations;
}
