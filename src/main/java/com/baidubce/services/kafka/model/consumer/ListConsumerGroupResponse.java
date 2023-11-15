package com.baidubce.services.kafka.model.consumer;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListConsumerGroupResponse extends AbstractBceResponse {

    private List<Group> groups;
}
