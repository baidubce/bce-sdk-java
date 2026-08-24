package com.baidubce.services.bes.v3.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.io.Serializable;

public class AbstractBesV3Request extends AbstractBceRequest implements Serializable {
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
