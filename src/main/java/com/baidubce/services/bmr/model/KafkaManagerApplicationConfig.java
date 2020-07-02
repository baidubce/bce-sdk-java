/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bmr.model;

/**
 * Represent a kafka-manager applicaton.
 * <p>
 * A kafka-manager application can be configured with property of version.
 */
public class KafkaManagerApplicationConfig extends ApplicationConfig {
    private static final String KAFKA_APPLICATION = "kafka-manager";

    public KafkaManagerApplicationConfig() { this.setName(KAFKA_APPLICATION); }

    /**
     * Configure the version of kafka-manager.
     * The reference version is as follows:
     * <p>
     * image type |  image version | kafka-manager version supported
     * hadoop    |    2.1.0       |    2.0.0.2
     *
     * @param version The version of kafka-manager.
     *
     * @return KafkaManagerApplicationConfig
     */
    public KafkaManagerApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
