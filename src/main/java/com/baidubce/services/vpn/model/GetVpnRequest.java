package com.baidubce.services.vpn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 *  Obtain vpn Details
 */
@Data
public class GetVpnRequest extends AbstractBceRequest {

    /**vpnId*/
    private String vpnId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
