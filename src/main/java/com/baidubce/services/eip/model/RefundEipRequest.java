package com.baidubce.services.eip.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for refunding prepaid EIP.
 */
@Getter
@Setter
public class RefundEipRequest extends AbstractBceRequest {
    /**
     * Client token for idempotency
     */
    @JsonIgnore
    private String clientToken;

    /**
     * EIP address to be refunded
     */
    @JsonIgnore
    private String eip;

    public RefundEipRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public RefundEipRequest withEip(String eip) {
        this.eip = eip;
        return this;
    }

    public RefundEipRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
