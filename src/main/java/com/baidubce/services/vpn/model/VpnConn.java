package com.baidubce.services.vpn.model;

import lombok.Data;

import java.util.List;

/**
 * vpn Tunnel instance
 */
@Data
public class VpnConn {
    /**the id of vpn*/
    private String vpnId;
    /** Tunneled id*/
    private String vpnConnId;
    /** The name of the tunnel */
    private String vpnConnName;
    /** local ip*/
    private String localIp;
    /** Shared secret */
    private String secretKey;
    /** Local network cidr List */
    private List<String> localSubnets;
    /** Peer VPN Gateway public network IP*/
    private String remoteIp;
    /** Peer network cidr List */
    private List<String> remoteSubnets;
    /** description */
    private String description;
    /**vpn Status of the tunnel */
    private String status;
    /** Creation time */
    private String createdTime;
    /** Unicom status */
    private String healthStatus;
    /**IKE Configuration */
    private IkeConfig ikeConfig;
    /**IPSec Configuration*/
    private IpsecConfig ipsecConfig;
}
