package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateVpnResponse extends AbstractBceResponse {
    private String vpnId;
}
