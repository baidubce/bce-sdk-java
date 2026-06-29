package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for creating or updating a MongoDB security IP group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateSecurityIpGroupRequest extends GenericMongodbRequest {

    private String name;

    private List<String> ips;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }
}
