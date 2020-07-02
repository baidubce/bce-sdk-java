package com.baidubce.services.bmr.model;

public class ZeppelinApplicationConfig  extends ApplicationConfig{

    private static final String ZEPPELIN_APPLICATION = "zeppelin";


    public ZeppelinApplicationConfig() {
        this.setName(ZEPPELIN_APPLICATION);
    }


    /**
     * Configure the version of zeppelin.
     * The reference version is as follows:
     * <p>
     * image type |  image version | zeppelin version supported
     * hadoop    |    2.0.0       |    0.8.0
     * @param version The version of zeppelin.
     *
     * @return ZeppelinApplicationConfig
     */
    public ZeppelinApplicationConfig withVersion(String version){
        this .setVersion(version);
        return this;
    }
}
