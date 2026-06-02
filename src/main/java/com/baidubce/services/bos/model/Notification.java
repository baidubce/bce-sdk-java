package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Notification {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Encryption {
        private String key;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class App {
        private String id;
        @JsonProperty("eventUrl")
        private String eventUrl;
        @JsonProperty("xVars")
        private String xVars;
    }

    private String id;
    private String name;
    @JsonProperty("appId")
    private String appId;
    private String status;
    private Encryption encryption;
    private List<String> resources;
    private List<String> events;
    private List<App> apps;
}