package com.baidubce.services.bvw.model.matlib;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Draft request.
 */
public class GetDraftRequest extends AbstractBceRequest {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GetDraftRequest(long id) {
        this.id = id;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public static GetDraftRequest of(long id) {
        return new GetDraftRequest(id);
    }

}
