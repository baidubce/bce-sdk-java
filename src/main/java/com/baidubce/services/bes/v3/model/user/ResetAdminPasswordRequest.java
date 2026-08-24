package com.baidubce.services.bes.v3.model.user;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ResetAdminPasswordRequest extends AbstractBesV3Request {
    private String clusterId;
    private String newPassword;
}
