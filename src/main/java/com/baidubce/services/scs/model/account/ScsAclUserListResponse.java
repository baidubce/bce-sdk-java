package com.baidubce.services.scs.model.account;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response of scs acl user list.
 */
public class ScsAclUserListResponse extends AbstractBceResponse {

    private Boolean success;
    private List<ScsAclUser> result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ScsAclUser> getResult() {
        return result;
    }

    public void setResult(List<ScsAclUser> result) {
        this.result = result;
    }
}
