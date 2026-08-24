package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

import java.util.List;

@Data
public class CreateIsmPolicyRequest extends AbstractBesV3Request {
    private String clusterId;
    private String policyId;
    private String description;
    private List<IsmTemplate> ismTemplate;
    private IsmRollover rollover;
    private IsmWarm warm;
    private IsmCold cold;
    private IsmForceMerge forceMerge;
    private IsmDelete delete;
}
