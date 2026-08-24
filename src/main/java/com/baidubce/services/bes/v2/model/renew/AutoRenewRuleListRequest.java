package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list auto renew rules.
 */
@Data
public class AutoRenewRuleListRequest extends AbstractBesV2Request {
    private String clusterId;
    private String serviceType;
}
