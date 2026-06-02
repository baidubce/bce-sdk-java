package com.baidubce.services.bos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Inventory {
    private String id;
    private String status;
    private List<String> resource;
    private String schedule;
    private int monthlyDate;
    private Destination destination;

    // All/Current
    private String includedObjectVersions;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Destination {
        private String targetBucket;
        private String targetPrefix;
        private String format;
    }

}
