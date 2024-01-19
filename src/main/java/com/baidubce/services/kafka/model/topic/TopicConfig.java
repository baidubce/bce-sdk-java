package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class TopicConfig {

    private String key;

    private Object value;

    private String unit;
}
