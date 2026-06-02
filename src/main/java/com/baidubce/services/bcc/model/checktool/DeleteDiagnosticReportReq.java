package com.baidubce.services.bcc.model.checktool;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class DeleteDiagnosticReportReq extends AbstractBceRequest {

    private List<String> reportIds;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
