package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class ClientIp extends JsonObject {
    private boolean enabled;
    private String name;

    /**
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled Whether to turn on IP single node access limit
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param enabled Whether to turn on IP single node access limit
     * @return this object
     */
    public ClientIp withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name ip type, 'True-Client-Ip' or 'X-Real-IP'
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param name ip type, 'True-Client-Ip' or 'X-Real-IP'
     * @return this object
     */
    public ClientIp withName(String name) {
        this.name = name;
        return this;
    }
}
