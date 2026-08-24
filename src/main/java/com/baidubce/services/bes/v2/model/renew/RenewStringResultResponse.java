package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 renew operations whose result is a constant string.
 */
@Data
public class RenewStringResultResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private String result;
}
