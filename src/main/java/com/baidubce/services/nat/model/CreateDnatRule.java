/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.nat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDnatRule {

    /**
     * The name of the dnat rule
     */
    private String ruleName;

    /**
     * The public ip address
     */
    private String publicIpAddress;

    /**
     * The private ip address
     */
    private String privateIpAddress;

    /**
     * The type of protocol
     */
    private String protocol;

    /**
     * The public port
     */
    private int publicPort;

    /**
     * The private port
     */
    private int privatePort;
}
