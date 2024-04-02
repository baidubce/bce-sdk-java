package com.baidubce.services.rds;

import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URL;

/**
 * Base rds test
 */
class BaseRdsTest {

    protected RdsConfig rdsConfig;

    protected RdsClientConfiguration setup(String path) {
        if (StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Please setup with config file path");
        }
        rdsConfig = readJson(path, RdsConfig.class);
        if (rdsConfig == null) {
            throw new IllegalArgumentException("RdsConfig is null");
        }
        RdsClientConfiguration clientConfiguration = new RdsClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);
        clientConfiguration.setCredentials(new DefaultBceCredentials(rdsConfig.getAk(), rdsConfig.getSk()));
        clientConfiguration.setEndpoint(rdsConfig.getEndpoint());
        return clientConfiguration;
    }

    protected <T> T readJson(String file, Class<T> clazz) {
        URL resource = getClass().getClassLoader().getResource(file);
        if (resource != null) {
            try {
                return JsonUtils.loadFrom(resource.openStream(), clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
