package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to create an auto renew rule.
 */
@Data
public class AutoRenewRuleCreateRequest extends AbstractBesV2Request {
    private List<String> clusterIds;
    private String renewTimeUnit;
    private Integer renewTime;
    private String serviceType;
}
