package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class LogCollectorStatusResponse extends AbstractBceResponse {
    private Boolean deployed;
    private String operationStatus;
    private String operationType;
    private String operationId;
}
