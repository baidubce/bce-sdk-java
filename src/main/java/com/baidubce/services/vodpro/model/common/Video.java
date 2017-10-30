package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created on 2017/8/7 18:38.
 *
 * @author yuchenbei@baidu.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private int index;
    private int widthInPixel;
    private int heightInPixel;
    private int codecId;
    private String codecName;
    private Double bitRateInKbps;
    private Double frameRate;

    public static Video fromJson(String json) {
        try {
            return objectMapper.readValue(json, Video.class);
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
