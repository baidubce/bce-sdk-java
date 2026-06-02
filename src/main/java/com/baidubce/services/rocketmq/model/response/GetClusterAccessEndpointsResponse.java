package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.AccessEndpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetClusterAccessEndpointsResponse extends RocketMQBaseResponse{
    private List<AccessEndpoint> accessEndpoints;
}
