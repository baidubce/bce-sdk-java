package com.baidubce.services.iothisk.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The base class of all pki request.
 */
public abstract class IotPkiManageRequest extends AbstractBceRequest {

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
