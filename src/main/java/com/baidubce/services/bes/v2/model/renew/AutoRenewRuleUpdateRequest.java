package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to update an auto renew rule.
 */
@Data
public class AutoRenewRuleUpdateRequest extends AbstractBesV2Request {
    private String clusterId;
    private String renewTimeUnit;
    private Integer renewTime;
}
