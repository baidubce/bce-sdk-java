package com.baidubce.services.scs;

import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;

/**
 * BaseScsClientTest
 */
public class BaseScsClientTest {

    protected ScsClientConfiguration configuration;
    protected ScsConfig scsConfig;

    protected void setup(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("Please setup with config file path");
        }
        scsConfig = readJson(filePath, ScsConfig.class);
        if (scsConfig != null) {
            setup(scsConfig.getAk(), scsConfig.getSk(), scsConfig.getEndpoint());
        } else {
            throw new RuntimeException("ScsConfig is null");
        }
    }

    protected void setup(String ak, String sk, String endpoint) {
        configuration = new ScsClientConfiguration();
        configuration.setProtocol(Protocol.HTTP);
        configuration.setCredentials(new DefaultBceCredentials(ak, sk));
        configuration.setEndpoint(endpoint);
    }

    @Nullable
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

    public void print(Logger logger, String method, Object obj) {
        try {
            String json = JsonUtils.toJsonPrettyString(obj);
            logger.info("[{}]==>\n{}", method, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}