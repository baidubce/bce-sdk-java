package com.baidubce.services.bcm.model.alarmhouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
public class Policy {
    private String name;
    private String aliasName;
    private String content;
    private String contentEn;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> extra;
}
