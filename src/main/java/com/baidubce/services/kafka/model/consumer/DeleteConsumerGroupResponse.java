package com.baidubce.services.kafka.model.consumer;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class DeleteConsumerGroupResponse extends AbstractBceResponse {

    private String groupName;
}
