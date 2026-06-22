package com.baidubce.services.nat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dnat rule info model
 */
@Getter
@Setter
@ToString
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
    private Integer publicPort;

    /**
     * The private port
     */
    private Integer privatePort;

    /**
     * The range of public port
     */
    private String publicPortRange;

    /**
     * The range of private port
     */
    private String privatePortRange;

    /**
     * The status of the dnat rule
     */
    String status;
}
