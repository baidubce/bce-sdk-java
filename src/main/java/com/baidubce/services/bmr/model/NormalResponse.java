package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

public class NormalResponse extends AbstractBceResponse {
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
