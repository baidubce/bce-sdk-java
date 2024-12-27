package com.baidubce.services.bcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecycleInstanceModel {
    private String id;
    private String serialNumber;
    private Date recycleTime;
    private Date deleteTime;
    private String serviceName;
    private String serviceType;
    private ConfigItem configItem;
    private List<String> configItems;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConfigItem {
        private int cpu;
        private int memory;
        private String type;
        private String specId;
        private String spec;
        private String zoneName;
    }
}
