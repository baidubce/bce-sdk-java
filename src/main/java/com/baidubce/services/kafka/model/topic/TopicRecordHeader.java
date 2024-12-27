package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class TopicRecordHeader {

    private String key;

    private String value;
}
