/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bmr.model;

/**
 * Represent a kafka applicaton.
 * <p>
 * A kafka application can be configured with property of version.
 */
public class KafkaApplicationConfig extends ApplicationConfig {
    private static final String KAFKA_APPLICATION = "kafka";

    public KafkaApplicationConfig() { this.setName(KAFKA_APPLICATION); }

    /**
     * Configure the version of kafka.
     * The reference version is as follows:
     * <p>
     * image type |  image version | kafka version supported
     * hadoop    |    2.1.0       |    2.0.1
     *
     * @param version The version of kafka.
     *
     * @return KafkaApplicationConfig
     */
    public KafkaApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
