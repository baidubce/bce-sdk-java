package com.baidubce.services.kafka.model.acl;

import lombok.Data;

@Data
public class Acl {

    private String username;

    private String patternType;

    private String resourceType;

    private String resourceName;

    private String operation;
}
