package com.baidubce.services.bcc.model.task;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OperationProgressUO {

    private String resourceId;

    private String operationStatus;

    private String code;

    private String errorMessage;


}
