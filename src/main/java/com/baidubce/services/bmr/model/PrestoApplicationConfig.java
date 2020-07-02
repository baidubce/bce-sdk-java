package com.baidubce.services.bmr.model;

public class PrestoApplicationConfig extends ApplicationConfig{
    private static final String Presto_APPLICATION = "presto";


    public PrestoApplicationConfig() {
        this.setName(Presto_APPLICATION);
    }


    /**
     * Configure the version of presto.
     * The reference version is as follows:
     * <p>
     * image type |  image version | presto version supported
     * hadoop    |    2.0.0       |    0.219
     * @param version The version of presto.
     *
     * @return PrestoApplicationConfig
     */
    public PrestoApplicationConfig withVersion(String version){
        this .setVersion(version);
        return this;
    }
}
