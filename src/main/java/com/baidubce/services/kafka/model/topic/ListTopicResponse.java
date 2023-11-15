package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListTopicResponse extends AbstractBceResponse {

    private List<Topic> topics;
}
