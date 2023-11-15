package com.baidubce.services.vpc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * The vpc private ip address info model
 */
@Data
public class VpcPrivateIpAddress {

    /**
     * The cidr of this vpc private ip
     */
    private String cidr;

    /**
     * The address of this vpc private ip
     */
    private String privateIpAddress;

    /**
     * The type of this vpc private ip address
     */
    private String privateIpAddressType;

    /**
     * The created time of this vpc private ip
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date createdTime;
}
