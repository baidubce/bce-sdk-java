package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 inspect operations whose success is a string and result is empty.
 */
@Data
public class InspectStringSuccessCommonResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Object result;
}
