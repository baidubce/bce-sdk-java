package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class QueryNodeSpec {
    private String nodeType;
    private String displayName;
    private Integer cpu;
    private Integer memory;
    private String description;
    private String status;
}
