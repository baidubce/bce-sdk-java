package com.baidubce.services.bcm.model.alarmhouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
public class Metric {
    private String name;
    private Long value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> dimensions;
    private String aliasName;
    private String aliasNameEn;
    private String unit;
}
