package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class EnableAuditRequest extends AbstractBesV3Request {
    private String clusterId;
    private String targetClusterId;
}
