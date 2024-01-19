package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class DeleteClusterConfigResponse extends AbstractBceResponse {

    /**
     * The id of config.
     */
    private String configId;
}
