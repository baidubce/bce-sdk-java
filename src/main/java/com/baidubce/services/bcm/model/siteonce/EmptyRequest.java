package com.baidubce.services.bcm.model.siteonce;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class EmptyRequest extends AbstractBceRequest {
    @Override
    public EmptyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
