package com.baidubce.services.bec.model.vo;

import lombok.Data;

/**
 * Subnet instance
 */
@Data
public class SubnetVo {

    /**
     * The subnetId of the subnet instance.
     */
    private String subnetId;

    /**
     * The name of the subnet instance.
     */
    private String name;

    /**
     * The cidr of the subnet instance.
     */
    private String cidr;

    /**
     * The description of the subnet instance.
     */
    private String description;

    /**
     * The regionId of the subnet instance.
     */
    private String regionId;
}
