package com.baidubce.services.eo.model;

/**
 * The base response for operations that only return a success flag, e.g. create/update/delete.
 */
public class SuccessResponse extends EoResponse {
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
