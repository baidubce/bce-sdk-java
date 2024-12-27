package com.baidubce.services.vpc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The VPC resource IP model.
 */
@Getter
@Setter
@ToString
public class VpcResourceIp {
    /**
     * Recource's IP address
     */
    private String ip;
    /**
     * Recource's type
     */
    private String resourceType;
}
