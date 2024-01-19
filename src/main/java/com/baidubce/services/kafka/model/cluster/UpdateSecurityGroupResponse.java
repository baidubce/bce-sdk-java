package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class UpdateSecurityGroupResponse extends AbstractBceResponse {

    private String clusterId;

    private String jobId;
}
