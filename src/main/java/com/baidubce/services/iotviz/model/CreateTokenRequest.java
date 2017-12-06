package com.baidubce.services.iotviz.model;

import com.baidubce.services.iothub.model.BaseRequest;

/**
 * Request to create a token
 */
public class CreateTokenRequest extends BaseRequest {
    private int ttl;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
