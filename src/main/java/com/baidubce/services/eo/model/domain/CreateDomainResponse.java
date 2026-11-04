package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of creating an accelerate domain.
 */
public class CreateDomainResponse extends EoResponse {
    /**
     * The status. "OPERATING" right after a successful creation.
     */
    private String status;

    /**
     * The generated cname domain.
     */
    private String cname;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }
}
