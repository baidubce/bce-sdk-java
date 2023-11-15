package com.baidubce.services.blb.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response for create appBlb policy
 */
public class CreateAppPolicyResponse extends AbstractBceResponse {

    /**
     * the ids of the policy.
     */
    private List<String> idList;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
