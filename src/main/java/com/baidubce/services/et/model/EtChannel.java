package com.baidubce.services.et.model;

import java.util.List;

import lombok.ToString;

/**
 * The model for describing ET channel.
 */
@ToString
public class EtChannel {
    /**
     * ET channel id
     */
    private String id;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Channel status. Its value range is ack-wait/accept/closed/pay-wait/reject/building/established, which
     * respectively correspond to Applying/Application accepted/Expired/Pending payment/Application
     * rejected/Constructing/Available
     */
    private String status;

    /**
     * Cloud network interconnection IP
     */
    private String baiduAddress;

    /**
     * Allocation Object
     */
    private List<String> authorizedUsers;


    /**
     * Routing parameter. Only when “routeType” is "static-route", the routing parameter is available
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
     * BGP ASN. Its valid range is 1-4294967295, and Baidu AI Cloud ASN is 45085
     */
    private String bgpAsn;

    /**
     * BGP key. Only when the “routeType” is “bgp”
     */
    private String bgpKey;

    /**
     * enable ipv6 or not, 1 means enable and 0 means disable
     */
    private Integer enableIpv6;

    /**
     * Cloud network interconnection IPv6 IP
     */
    private String baiduIpv6Address;

    /**
     * IDC Interconnection IPv6 IP
     */
    private String customerIpv6Address;

    /**
     * IPv6 routing parameter
     */
    private List<String> ipv6Networks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBaiduAddress() {
        return baiduAddress;
    }

    public void setBaiduAddress(String baiduAddress) {
        this.baiduAddress = baiduAddress;
    }

    public List<String> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(List<String> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
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

    public String getBgpAsn() {
        return bgpAsn;
    }

    public void setBgpAsn(String bgpAsn) {
        this.bgpAsn = bgpAsn;
    }

    public String getBgpKey() {
        return bgpKey;
    }

    public void setBgpKey(String bgpKey) {
        this.bgpKey = bgpKey;
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
