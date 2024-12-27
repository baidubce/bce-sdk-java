package com.baidubce.services.kafka.model.topic;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class QueryTopicMessagesByStartTimeResponse extends AbstractBceResponse {

    private List<QueryTopicRecord> messages;
}
