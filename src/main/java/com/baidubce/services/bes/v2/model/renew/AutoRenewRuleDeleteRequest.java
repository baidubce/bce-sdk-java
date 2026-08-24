package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to delete an auto renew rule.
 */
@Data
public class AutoRenewRuleDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
}
