package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GetSslVpnServerResponse extends AbstractBceResponse {
    /**
     * id of vpn
     */
    private String vpnId;

    /**
     * id of sslVpnServer
     */
    private String sslVpnServerId;

    /**
     * SSL-VPN server name.
     */
    private String sslVpnServerName;

    /**
     * SSL-VPN server interface type. Values [tap, tun], default is 'tap'.
     */
    private String interfaceType;

    /**
     * Local network CIDR list
     */
    private List<String> localSubnets;

    /**
     * Client network CIDR
     */
    private String remoteSubnet;

    /**
     * Client's DNS addresses
     */
    private String clientDns;

    /**
     * status of sslvpnServer
     */
    private String status;


    private Integer maxConnection;
}
