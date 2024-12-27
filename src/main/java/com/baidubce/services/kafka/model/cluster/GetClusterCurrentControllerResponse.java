package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class GetClusterCurrentControllerResponse extends AbstractBceResponse {

    private Controller controller;
}
