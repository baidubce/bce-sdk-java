package com.baidubce.services.bmr.model;

/**
 * Represent a Hdfs application.
 * <p>
 * A Hdfs application can be configured with property of version.
 */
public class HdfsApplicationConfig extends ApplicationConfig {
    private static final String HDFS_APPLICATION = "hdfs";

    public HdfsApplicationConfig() {
        this.setName(HDFS_APPLICATION);
    }

    /**
     * Configure the version of Hdfs.
     * The reference version is as follows:
     * <p>
     * image type |  image version      |   hdfs version supported
     * hadoop     |  1.0.0/1.1.0/1.2.0  |   2.7.1
     * hadoop     |  2.0.0              |   3.1.1
     *
     * @param version The version of HDFS.
     *
     * @return HdfsApplicationConfig
     */
    public HdfsApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
