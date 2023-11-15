package com.baidubce.services.kafka.model.job;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetJobDetailResponse extends AbstractBceResponse {

    private Job job;
}