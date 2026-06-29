package com.baidubce.services.mongodb.model;

import java.util.Collection;

/**
 * Request for adding or deleting whitelist security IPs.
 */
public class MongodbSecurityIpRequest extends GenericMongodbRequest {
    private Collection<String> securityIps;

    public Collection<String> getSecurityIps() {
        return securityIps;
    }

    public void setSecurityIps(Collection<String> securityIps) {
        this.securityIps = securityIps;
    }
}
