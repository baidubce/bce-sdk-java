package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the auto renew rule detail.
 */
@Data
public class AutoRenewRuleDetailRequest extends AbstractBesV2Request {
    private String clusterId;
}
