package com.baidubce.services.bes.v3.model.bct;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class BctAuthSwitchResponse extends AbstractBceResponse {
    private Boolean enabled;
}
