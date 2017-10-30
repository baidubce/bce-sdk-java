package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created on 2017/8/7 19:14.
 *
 * @author yuchenbei@baidu.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String album;
    private String composer;
    private String genre;
    private String title;
    private String artist;
    private String albumArtist;
    private String track;
    private String rotate;

    public static Tag fromJson(String json) {
        try {
            return objectMapper.readValue(json, Tag.class);
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
