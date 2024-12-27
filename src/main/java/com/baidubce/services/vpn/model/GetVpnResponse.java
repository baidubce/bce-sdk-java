/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tag.model.Tag;

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
    /**
     * The type of vpn, "IPSec" means IPSec-VPN, and "SSL" means SSL-VPN.
     */
    private String type;
    /**
     * The delete protect switch of vpn.
     */
    private Boolean deleteProtect;
    /**
     * The max client connection number of SSL_VPN.
     */
    private Integer maxConnection;
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
    /**
     * The SSL-VPN server info.
     */
    private GetSslVpnServerResponse sslVpnServer;
    /**
     * The tags of vpn.
     */
    private List<Tag> tags;
    /**
     * the create time of vpn
     */
    private String createTime;
}
