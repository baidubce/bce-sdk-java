package com.baidubce.services.oos.model.response;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.oos.model.Execution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseExecutionResponse extends AbstractBceResponse {
    private boolean success;
    private String msg;
    private Integer code;
    private Execution result;
}
