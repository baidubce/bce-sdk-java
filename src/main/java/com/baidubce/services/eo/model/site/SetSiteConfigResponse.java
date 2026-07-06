package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of setting configuration of a site.
 */
public class SetSiteConfigResponse extends EoResponse {
    /**
     * The status of modifying the configuration. It is "RUNNING" when the configuration
     * is set successfully, otherwise an error message is returned.
     */
    private String status;

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
}
