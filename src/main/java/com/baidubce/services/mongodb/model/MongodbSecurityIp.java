package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Whitelist security IP information of a MongoDB instance, matching the server-side
 * {@code SecurityIp}: plain IPs, security-group bound IPs and template bound IPs.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbSecurityIp extends GenericMongodbResponse {
    private Collection<String> securityIps = new ArrayList<String>();

    private List<MongodbGroupSecurityIp> groupSecurityIps = new LinkedList<MongodbGroupSecurityIp>();

    private List<MongodbGroupSecurityIp> templateSecurityIps = new LinkedList<MongodbGroupSecurityIp>();

    public Collection<String> getSecurityIps() {
        return securityIps;
    }

    public void setSecurityIps(Collection<String> securityIps) {
        this.securityIps = securityIps;
    }

    public List<MongodbGroupSecurityIp> getGroupSecurityIps() {
        return groupSecurityIps;
    }

    public void setGroupSecurityIps(List<MongodbGroupSecurityIp> groupSecurityIps) {
        this.groupSecurityIps = groupSecurityIps;
    }

    public List<MongodbGroupSecurityIp> getTemplateSecurityIps() {
        return templateSecurityIps;
    }

    public void setTemplateSecurityIps(List<MongodbGroupSecurityIp> templateSecurityIps) {
        this.templateSecurityIps = templateSecurityIps;
    }
}
