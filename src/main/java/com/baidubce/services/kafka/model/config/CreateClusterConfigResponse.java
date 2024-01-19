package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateClusterConfigResponse extends AbstractBceResponse {

    private String configId;
}
