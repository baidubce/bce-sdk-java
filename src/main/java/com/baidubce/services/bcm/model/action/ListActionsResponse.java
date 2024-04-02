package com.baidubce.services.bcm.model.action;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.Page;
import lombok.Data;

@Data
public class ListActionsResponse extends AbstractBceResponse {
    private String requestId;
    private String message;
    private boolean success;
    private int code;
    private Page<Action> result;
}