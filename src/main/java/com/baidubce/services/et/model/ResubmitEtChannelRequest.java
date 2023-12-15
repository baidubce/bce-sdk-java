package com.baidubce.services.et.model;

import java.util.List;

import lombok.ToString;

/**
 * The request for resubmitting an ET channel.
 */
@ToString
public class ResubmitEtChannelRequest extends EtChannelIdRequest {
    /**
     * Allocation Object
     */
    private List<String> authorizedUsers;

    /**
     * Description
     */
    private String description;

    /**
     * Cloud network interconnection IP
     */
    private String baiduAddress;

    /**
     * Channel Name
     */
    private String name;

    /**
     * Routing parameter. Only when “routeType” is "static-route", the routing parameter is required
     */
    private List<String> networks;

    /**
     * IDC Interconnection IP
     */
    private String customerAddress;

    /**
     * Routing protocol. Currently, only “static-route” (static) and "bgp” (dynamic) are available
     */
    private String routeType;

    /**
     * VLAN ID, Value range: 1- 4094
     */
    private Integer vlanId;

    /**
     * enable ipv6 or not, 1 means enable and 0 means disable
     */
    private Integer enableIpv6;

    /**
     * Cloud network interconnection IPv6 IP, required when enableIpv6=1
     */
    private String baiduIpv6Address;

    /**
     * IDC Interconnection IPv6 IP, required when enableIpv6=1
     */
    private String customerIpv6Address;

    /**
     * IPv6 routing parameter. Only when “routeType” is "static-route" and enableIpv6=1, the IPv6 routing parameter
     * is required
     */
    private List<String> ipv6Networks;

    public List<String> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(List<String> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaiduAddress() {
        return baiduAddress;
    }

    public void setBaiduAddress(String baiduAddress) {
        this.baiduAddress = baiduAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public void setNetworks(List<String> networks) {
        this.networks = networks;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public Integer getVlanId() {
        return vlanId;
    }

    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    public Integer getEnableIpv6() {
        return enableIpv6;
    }

    public void setEnableIpv6(Integer enableIpv6) {
        this.enableIpv6 = enableIpv6;
    }

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
