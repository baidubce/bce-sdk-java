package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class MigrateClusterAzResponse extends AbstractBceResponse {

    private String clusterId;

    private String actionId;
}
