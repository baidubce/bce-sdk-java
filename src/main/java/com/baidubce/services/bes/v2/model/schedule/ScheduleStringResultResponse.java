package com.baidubce.services.bes.v2.model.schedule;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 schedule operations whose result is a constant string.
 */
@Data
public class ScheduleStringResultResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private String result;
}
