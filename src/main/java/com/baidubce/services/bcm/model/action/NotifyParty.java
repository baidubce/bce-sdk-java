package com.baidubce.services.bcm.model.action;

import lombok.Data;

@Data
public class NotifyParty {
    private String id;

    private String domainId;

    private String name;

    private String email;

    private String phone;

    private String type;
}