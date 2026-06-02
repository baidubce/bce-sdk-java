package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class AclUser {
    private String name;
    private Boolean admin;
    private String finalPermission;
}
