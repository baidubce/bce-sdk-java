package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * create by changxing01 on 19/8/27
 */
public class CheckDomainValidResponse extends CdnResponse {

    @JsonProperty("isValid")
    private boolean isValid;
    private String message;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the reason of not
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
