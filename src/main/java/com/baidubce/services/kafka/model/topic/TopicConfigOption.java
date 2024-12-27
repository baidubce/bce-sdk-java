package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class TopicConfigOption {

    private String name;

    private Object defaultValue;

    private String description;

    private String type;

    private String unit;

    private Object[] valueScope;
}
