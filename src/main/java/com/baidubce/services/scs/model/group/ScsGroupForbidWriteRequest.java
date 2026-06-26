package com.baidubce.services.scs.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsGroupForbidWriteRequest extends AbstractBceRequest {
    private Boolean forbidWriteFlag;

    public Boolean getForbidWriteFlag() {
        return forbidWriteFlag;
    }

    public void setForbidWriteFlag(Boolean forbidWriteFlag) {
        this.forbidWriteFlag = forbidWriteFlag;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
