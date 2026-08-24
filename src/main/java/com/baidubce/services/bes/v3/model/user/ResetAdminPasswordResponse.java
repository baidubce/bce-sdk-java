package com.baidubce.services.bes.v3.model.user;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ResetAdminPasswordResponse extends AbstractBceResponse {
    private Boolean success;
}
