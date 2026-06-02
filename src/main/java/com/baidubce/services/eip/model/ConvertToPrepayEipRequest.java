package com.baidubce.services.eip.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for converting postpaid EIP to prepaid EIP.
 */
@Getter
@Setter
public class ConvertToPrepayEipRequest extends AbstractBceRequest {
    /**
     * Client token for idempotency
     */
    @JsonIgnore
    private String clientToken;

    /**
     * EIP address to be converted
     */
    @JsonIgnore
    private String eip;

    /**
     * Purchase length in months (1-9, 12, 24, 36)
     */
    private Integer purchaseLength;

    /**
     * Bandwidth in Mbps for prepaid EIP
     */
    private Integer bandWidth;

    public ConvertToPrepayEipRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public ConvertToPrepayEipRequest withEip(String eip) {
        this.eip = eip;
        return this;
    }

    public ConvertToPrepayEipRequest withPurchaseLength(Integer purchaseLength) {
        this.purchaseLength = purchaseLength;
        return this;
    }

    public ConvertToPrepayEipRequest withBandWidth(Integer bandWidth) {
        this.bandWidth = bandWidth;
        return this;
    }

    public ConvertToPrepayEipRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}