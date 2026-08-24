package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class IsmPolicyDetailResult extends AbstractBceResponse {
    private String policyId;
    private String rawContent;
    private String description;
    private List<IsmTemplate> ismTemplate;
    private String lastUpdatedTime;
    private IsmRollover rollover;
    private IsmCold cold;
    private IsmForceMerge forceMerge;
    private IsmDelete delete;
}
