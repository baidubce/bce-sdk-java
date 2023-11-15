package com.baidubce.services.nat.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for list all nat rule info in one specified nat.
 */
@Getter
@Setter
public class ListNatRuleRequest extends ListRequest {

    /**
     * The id of the nat.
     */
    private String natId;

    /**
     * Configure natId for the request.
     *
     * @param natId The id of the nat
     * @return GetNatRuleRequest with the nat id
     */
    public ListNatRuleRequest withNatId(String natId) {
        this.natId = natId;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetNatRuleRequest with credentials.
     */
    @Override
    public ListNatRuleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
