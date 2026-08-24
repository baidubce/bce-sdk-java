package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class AuditStatusResponse extends AbstractBceResponse {
    private Boolean enabled;
}
