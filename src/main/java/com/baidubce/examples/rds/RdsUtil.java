package com.baidubce.examples.rds;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RdsUtil {
    public static <T> T readJson(String file, Class<T> clazz) {
        URL resource = clazz.getClassLoader().getResource(file);
        if (resource != null) {
            try (InputStream inputStream = resource.openStream()) {
                return new ObjectMapper().readValue(inputStream, clazz);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON from file: " + file, e);
            }
        }
        return null;
    }
    public static RdsClient createRdsClient() {
        String endPoint = "rds.bj.baidubce.com";
        String accessKey = "ak";
        String secretAccessKy = "sk";
        BceClientConfiguration config =
                new BceClientConfiguration()
                        .withCredentials(new DefaultBceCredentials(accessKey, secretAccessKy))
                        .withEndpoint(endPoint);
        RdsClient rdsClient = new RdsClient(config);
        return rdsClient;
    }

    public static void print(String method, Object obj) {
        try {
            String json = JsonUtils.toJsonPrettyString(obj);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
