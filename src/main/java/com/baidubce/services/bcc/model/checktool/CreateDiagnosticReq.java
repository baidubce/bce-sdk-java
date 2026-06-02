package com.baidubce.services.bcc.model.checktool;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class CreateDiagnosticReq extends AbstractBceRequest {

    private String metricSetId;

    private String instanceType;

    private String instanceId;

    private String pid;

    private Integer duration = 180;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
