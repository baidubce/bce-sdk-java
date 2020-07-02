package com.baidubce.services.bmr.model;

/**
 * Represent a Yarn application.
 * <p>
 * A Yarn application can be configured with property of version.
 */
public class YarnApplicationConfig extends ApplicationConfig {
    private static final String YARN_APPLICATION = "yarn";

    public YarnApplicationConfig() {
        this.setName(YARN_APPLICATION);
    }

    /**
     * Configure the version of Yarn.
     * The reference version is as follows:
     * <p>
     * image type |  image version      |   yarn version supported
     * hadoop     |  1.0.0/1.1.0/1.2.0  |   2.7.1
     * hadoop     |  2.0.0              |   3.1.1
     *
     * @param version The version of YARN.
     *
     * @return YARNApplicationConfig
     */
    public YarnApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
