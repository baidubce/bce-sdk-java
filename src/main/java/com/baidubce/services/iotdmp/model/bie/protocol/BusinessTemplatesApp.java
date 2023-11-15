package com.baidubce.services.iotdmp.model.bie.protocol;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessTemplatesApp extends AbstractBceResponse {
    private String name;
    private String description;
    private String selector;
    private String version;
    private String createTime;
    private Map<String, String> labels;
    private List<BusinessTemplatesAppService> services;
    private List<BusinessTemplatesAppService> initServices;
    private List<BusinessTemplatesAppRegistry> registries;
    private List<BusinessTemplatesAppVolume> volumes;
    private BieWorkload workload;
    private BieAppType type;
    private BieMode mode;
    private int replica;
}
