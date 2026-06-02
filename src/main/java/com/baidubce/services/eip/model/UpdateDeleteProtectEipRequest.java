package com.baidubce.services.eip.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for updating EIP delete protection switch.
 */
@Getter
@Setter
public class UpdateDeleteProtectEipRequest extends AbstractBceRequest {
    /**
     * Client token for idempotency
     */
    @JsonIgnore
    private String clientToken;

    /**
     * EIP address to update delete protection
     */
    @JsonIgnore
    private String eip;

    /**
     * Whether to enable delete protection
     */
    private Boolean deleteProtect;

    public UpdateDeleteProtectEipRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public UpdateDeleteProtectEipRequest withEip(String eip) {
        this.eip = eip;
        return this;
    }

    public UpdateDeleteProtectEipRequest withDeleteProtect(Boolean deleteProtect) {
        this.deleteProtect = deleteProtect;
        return this;
    }

    public UpdateDeleteProtectEipRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}