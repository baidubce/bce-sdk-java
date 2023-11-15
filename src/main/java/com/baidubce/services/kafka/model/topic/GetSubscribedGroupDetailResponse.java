package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetSubscribedGroupDetailResponse extends AbstractBceResponse {

    private List<GroupTopicPartition> subscribePartitions;
}
