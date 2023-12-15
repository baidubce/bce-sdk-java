package com.baidubce.services.vpn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class ListVpnRequest  extends ListRequest {

    /**Belonging VPC The identifier */
    private String vpcId;
    /**Bound eip address */
    private String eip;

    /**
     * the type of vpn.
     * IPSec means IPsec-VPN, SSL means SSL-VPN, default empty,means all vpn.
     */
    private String type;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


}
