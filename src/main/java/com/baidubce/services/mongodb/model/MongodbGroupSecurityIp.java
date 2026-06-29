package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A security group / template entry in a MongoDB whitelist, carrying its own IP list.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbGroupSecurityIp {
    private List<String> ips;
    private String name;
    private String uuid;

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
