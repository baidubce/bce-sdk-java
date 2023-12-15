package com.baidubce.services.etgateway.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class EtGateway {
    /**
     * dedicated line gateway ID
     */
    private String etGatewayId;

    /**
     * The name of the dedicated line gateway
     */
    private String name;

    /**
     * dedicated line gateway status
     */
    private String status;

    /**
     * dedicated line gateway egress bandwidt
     */
    private String speed;

    private String createTime;

    /**
     * The description of the dedicated line gateway
     */
    private String description;

    /**
     * vpcId
     */
    private String vpcId;

    /**
     * The ID of the bound physical dedicated line.
     */
    private String etId;

    /**
     * The ID of the bound dedicated line channel.
     */
    private String channelId;

    /**
     * IPv4 cloud-side network
     */
    private List<String> localCidrs;

    /**
     * Whether IPv6 functionality is enabled, 1 for yes, 0 for no.
     */
    private Integer enableIpv6;

    /**
     * IPv6 cloud-side network
     */
    private List<String> ipv6LocalCidrs;
}
