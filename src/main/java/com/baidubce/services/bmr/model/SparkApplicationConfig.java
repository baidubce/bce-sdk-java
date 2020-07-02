package com.baidubce.services.bmr.model;

/**
 * Represent a SPARK applicaton.
 * <p>
 * A SPARK application can be configured with property of version.
 */
public class SparkApplicationConfig extends ApplicationConfig {

    private static final String SPARK_APPLICATION = "spark";

    public SparkApplicationConfig() {
        this.setName(SPARK_APPLICATION);
    }

    /**
     * Configure the version of spark.
     * The reference version is as follows:
     * <p>
     * image type |  image version | spark version supported
     * hadoop    |    1.0.0       |    1.6.0
     * hadoop    |    1.1.0       |    2.1.0
     * hadoop    |    1.2.0       |    2.1.0
     * hadoop    |    2.0.0       |    2.3.2
     *
     * @param version The version of Spark.
     *
     * @return SparkApplicationConfig
     */
    public SparkApplicationConfig withVersion(String version) {
        if (version.equalsIgnoreCase("2.1.0") || version.equalsIgnoreCase("2.3.2") ||
                version.equalsIgnoreCase("2.4.4")) {
            this.setName("spark2");
            this.setVersion(version);
        } else {
            this.setVersion(version);
        }
        return this;
    }

}
