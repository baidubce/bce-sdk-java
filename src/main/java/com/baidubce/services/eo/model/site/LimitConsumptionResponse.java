package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of setting or deleting a consumption limit rule.
 */
public class LimitConsumptionResponse extends EoResponse {
    /**
     * Whether the operation succeeded.
     */
    private Boolean success;

    /**
     * @return success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
