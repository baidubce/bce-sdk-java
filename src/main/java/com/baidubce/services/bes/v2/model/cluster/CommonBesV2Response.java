package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 cluster operations.
 */
@Data
public class CommonBesV2Response extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Object result;
}
