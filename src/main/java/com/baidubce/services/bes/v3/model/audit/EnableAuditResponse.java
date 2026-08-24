package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class EnableAuditResponse extends AbstractBceResponse{
    private Boolean enabled;
    private String targetClusterId;
}