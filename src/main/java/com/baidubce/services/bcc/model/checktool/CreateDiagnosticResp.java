package com.baidubce.services.bcc.model.checktool;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateDiagnosticResp extends AbstractBceResponse {

    private String requestId;

    private String reportId;
}
