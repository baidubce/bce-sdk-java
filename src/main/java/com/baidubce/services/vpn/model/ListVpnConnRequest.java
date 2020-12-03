package com.baidubce.services.vpn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class ListVpnConnRequest extends ListRequest {

    /**vpn id  required*/
    private String vpnId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


}
