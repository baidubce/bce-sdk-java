package com.baidubce.services.bes.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.io.Serializable;

/**
 * Base class for BES v2 request objects.
 */
public abstract class AbstractBesV2Request extends AbstractBceRequest implements Serializable {
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
