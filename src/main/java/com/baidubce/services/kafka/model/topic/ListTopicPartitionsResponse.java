package com.baidubce.services.kafka.model.topic;

import com.baidubce.services.kafka.model.PageListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListTopicPartitionsResponse extends PageListResponse {

    private List<TopicPartition> partitions;
}
