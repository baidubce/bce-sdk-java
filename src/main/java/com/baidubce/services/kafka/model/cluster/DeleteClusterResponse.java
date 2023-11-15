package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class DeleteClusterResponse extends AbstractBceResponse {

    /**
     * The id of cluster.
     */
    private String clusterId;
}
