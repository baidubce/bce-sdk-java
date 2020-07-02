package com.baidubce.services.bmr.model;

/**
 * Represent a Zookeeper application.
 * <p>
 * A Zookeeper application can be configured with property of version.
 */
public class ZookeeperApplicationConfig extends ApplicationConfig {
    private static final String ZOOKEEPER_APPLICATION = "zookeeper";

    public ZookeeperApplicationConfig() {
        this.setName(ZOOKEEPER_APPLICATION);
    }

    /**
     * Configure the version of Zookeeper.
     * The reference version is as follows:
     * <p>
     * image type |  image version            |   zookeeper version supported
     * hadoop     |  1.0.0/1.1.0/1.2.0/2.0.0  |   3.4.6
     *
     * @param version The version of Zookeeper.
     *
     * @return ZookeeperApplicationConfig
     */
    public ZookeeperApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}