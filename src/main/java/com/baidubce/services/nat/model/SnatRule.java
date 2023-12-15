package com.baidubce.services.nat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Snat rule info model
 */
@Getter
@Setter
@ToString
public class SnatRule {

    /**
     * The id of the snat rule
     */
    String ruleId;

    /**
     * The name of the snat rule
     */
    String ruleName;

    /**
     * The list of the public ip addresses
     */
    List<String> publicIpsAddress;

    /**
     * The source cidr
     */
    String sourceCIDR;

    /**
     * The status of the snat rule
     */
    String status;
}
