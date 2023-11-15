package com.baidubce.services.nat.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Dnat rule info model
 */
@Getter
@Setter
public class DnatRule {

    /**
     * The id of the dnat rule
     */
    String ruleId;

    /**
     * The name of the dnat rule
     */
    String ruleName;

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
    private String publicPort;

    /**
     * The private port
     */
    private String privatePort;

    /**
     * The status of the dnat rule
     */
    String status;
}
