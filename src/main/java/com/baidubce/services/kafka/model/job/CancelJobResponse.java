package com.baidubce.services.kafka.model.job;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CancelJobResponse extends AbstractBceResponse {

    private String jobId;
}
