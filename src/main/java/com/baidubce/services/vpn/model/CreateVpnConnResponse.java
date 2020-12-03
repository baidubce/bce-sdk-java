package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateVpnConnResponse extends AbstractBceResponse {
    private String vpnConnId;
}
