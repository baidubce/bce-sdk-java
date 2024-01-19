package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateClusterConfigRevisionResponse extends AbstractBceResponse {
    private Integer revisionId;
}
