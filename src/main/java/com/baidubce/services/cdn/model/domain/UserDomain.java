package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class UserDomain extends JsonObject {
    private String domain;
    private String status;

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status domain's status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
