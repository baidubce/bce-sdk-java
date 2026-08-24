package com.baidubce.services.bes.v3.model.bct;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class UpdateBctAuthSwitchRequest extends AbstractBesV3Request {
    private Boolean enabled;
}
