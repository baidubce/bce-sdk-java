package com.baidubce.services.bec.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Vpc instance
 */
@Data
public class VpcVo {

    /**
     * The vpcId of the vpc instance.
     */
    private String vpcId;

    /**
     * The name of the vpc instance.
     */
    private String name;

    /**
     * The cidr of the vpc instance.
     */
    private String cidr;

    /**
     * The description of the vpc instance.
     */
    private String description;

    /**
     * The bindedSubnet of the vpc instance.
     */
    @JsonProperty("subnet")
    private SubnetVo bindedSubnet;
}
