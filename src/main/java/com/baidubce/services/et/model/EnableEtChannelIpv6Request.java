package com.baidubce.services.et.model;

import java.util.List;

import lombok.ToString;

/**
 * The request for enabling ET channel .
 */
@ToString
public class EnableEtChannelIpv6Request extends EtChannelIdRequest{
    /**
     * Cloud network interconnection IPv6 IP
     */
    private String baiduIpv6Address;

    /**
     * IDC Interconnection IPv6 IP
     */
    private String customerIpv6Address;

    /**
     * IPv6 routing parameter.
     */
    private List<String> ipv6Networks;

    public String getBaiduIpv6Address() {
        return baiduIpv6Address;
    }

    public void setBaiduIpv6Address(String baiduIpv6Address) {
        this.baiduIpv6Address = baiduIpv6Address;
    }

    public String getCustomerIpv6Address() {
        return customerIpv6Address;
    }

    public void setCustomerIpv6Address(String customerIpv6Address) {
        this.customerIpv6Address = customerIpv6Address;
    }

    public List<String> getIpv6Networks() {
        return ipv6Networks;
    }

    public void setIpv6Networks(List<String> ipv6Networks) {
        this.ipv6Networks = ipv6Networks;
    }
}
