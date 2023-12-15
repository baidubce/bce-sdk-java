package com.baidubce.services.vpn.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateVpnResponse extends AbstractBceResponse {
    private String vpnId;
}
