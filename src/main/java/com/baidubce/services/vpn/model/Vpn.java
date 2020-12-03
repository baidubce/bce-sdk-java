package com.baidubce.services.vpn.model;

import lombok.Data;

import java.util.List;

/**
 * vpn model
 */
@Data
public class Vpn {
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
