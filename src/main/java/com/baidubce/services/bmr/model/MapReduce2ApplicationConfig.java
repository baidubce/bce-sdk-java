package com.baidubce.services.bmr.model;

/**
 * Represent a MapReduce2 application.
 * <p>
 * A MapReduce2 application can be configured with property of version.
 */
public class MapReduce2ApplicationConfig extends ApplicationConfig {
    private static final String MAPREDUCE2_APPLICATION = "mapreduce2";

    public MapReduce2ApplicationConfig() {
        this.setName(MAPREDUCE2_APPLICATION);
    }

    /**
     * Configure the version of MapReduce2.
     * The reference version is as follows:
     * <p>
     * image type |  image version      |   mapreduce2 version supported
     * hadoop     |  1.0.0/1.1.0/1.2.0  |   2.7.1
     * hadoop     |  2.0.0              |   3.1.1
     *
     * @param version The version of MapReduce2.
     *
     * @return MapReduce2ApplicationConfig
     */
    public MapReduce2ApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}