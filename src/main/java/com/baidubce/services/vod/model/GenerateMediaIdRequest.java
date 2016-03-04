package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GenerateMediaIdRequest extends AbstractBceRequest {

    public GenerateMediaIdRequest() {
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

}
