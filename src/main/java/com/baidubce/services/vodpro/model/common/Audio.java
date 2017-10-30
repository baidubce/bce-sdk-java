package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created on 2017/8/7 18:41.
 *
 * @author yuchenbei@baidu.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private int index;
    private int codecId;
    private String codecName;
    private int sampleRateInHz;
    private int channels;
    private Double bitRateInKbps;

    public static Audio fromJson(String json) {
        try {
            return objectMapper.readValue(json, Audio.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJson() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
