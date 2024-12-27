package com.baidubce.services.kafka.model.cluster;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class GetClusterHistoryControllerResponse extends AbstractBceResponse {

    private List<Controller> controllers;
}
