package com.baidubce.services.bcm.model.action;

import lombok.Data;

@Data
public class ActionUserInfo {
    private String name;
    private String email;
    private String phone;
    private String type;
}