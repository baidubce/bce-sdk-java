package com.baidubce.services.bmr.model;

public class AzkabanApplicationConfig extends ApplicationConfig {
    private static final String Azkaban_APPLICATION = "azkaban";


    public AzkabanApplicationConfig() {
        this.setName(Azkaban_APPLICATION);
    }


    /**
     * Configure the version of azkaban.
     * The reference version is as follows:
     * <p>
     * image type |  image version | azkaban version supported
     * hadoop    |    2.0.0       |    3.58.0
     * @param version The version of azkaban.
     *
     * @return AzkabanApplicationConfig
     */
    public AzkabanApplicationConfig withVersion(String version){
        this .setVersion(version);
        return this;
    }
}
