package com.baidubce.services.bcm.model.alarmhouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
public class Resource {
    private String scope;
    private String resourceType;
    private String region;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> identifiers;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> properties;
}
