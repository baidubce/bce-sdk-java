/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * vpn  details
 */
@Data
public class GetVpnResponse extends AbstractBceResponse {
    /**the id of vpn*/
    private String vpnId;
    /** name */
    private String vpnName;
    /** description */
    private String description;
    /**vpn status ，value range ：active、building、unconfigured*/
    private String status;
    /** Expire date */
    private String expiredTime;
    /** Billing type */
    private String paymentTiming;
    /** public net ip*/
    private String eip;
    /**eip bandwidth */
    private Integer bandwidthInMbps;
    /**the id of vpc*/
    private String vpcId;
    /** Number of tunnels */
    private Integer vpnConnNum;
    /**VPN Tunnel list */
    private List<VpnConn> vpnConns;
}
