package com.baidubce.services.bcc.model.checktool;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ListDiagnosticReq extends AbstractBceRequest {

    private Integer maxKeys = 100;

    private String reportId;

    private String instanceType;

    private String instanceId;

    private String status;

    private String severity;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
