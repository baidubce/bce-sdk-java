package com.baidubce.services.etgateway.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.TagModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GetEtGatewayResponse extends AbstractBceResponse {
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

    /**
     * The source IP for health check.
     */
    private String healthcheckourceIp;

    /**
     * The destination IP for health check
     */
    private String healthCheckDestIp;

    /**
     * The interval for health check
     */
    private Integer healthCheckInterval;

    /**
     * The threshold for health check
     */
    private Integer healthThreshold;

    /**
     * The threshold for unhealthy status in health check
     */
    private Integer unhealthThreshold;

    /**
     * The method of health check.
     */
    private String healthCheckType;

    /**
     * The port for health check.
     */
    private Integer healthCheckPort;

    /**
     * Tag list
     */
    private List<TagModel> tags;
}
