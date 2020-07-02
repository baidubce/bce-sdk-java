/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bmr.model;

/**
 * Represent a impala applicaton.
 * <p>
 * A impala application can be configured with property of version.
 */
public class ImpalaApplicationConfig extends ApplicationConfig {
    private static final String IMPALA_APPLICATION = "impala";

    public ImpalaApplicationConfig() { this.setName(IMPALA_APPLICATION); }

    /**
     * Configure the version of impala.
     * The reference version is as follows:
     * <p>
     * image type |  image version | impala version supported
     * hadoop    |    2.0.0       |    3.2.0
     *
     * @param version The version of impala.
     *
     * @return ImpalaApplicationConfig
     */
    public ImpalaApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
