package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 log operations whose result is a plain string.
 */
@Data
public class LogStringResultResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private String result;
}
