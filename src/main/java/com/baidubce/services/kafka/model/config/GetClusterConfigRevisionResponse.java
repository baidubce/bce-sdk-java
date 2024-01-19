package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetClusterConfigRevisionResponse extends AbstractBceResponse {

    private ClusterConfigRevisionDetail revision;
}
