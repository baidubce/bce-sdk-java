package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a ListInstance operation.
 */
public class ListInstancesRequest extends AbstractBceRequest {
    /**
     * Constructs a new ListInstancesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ListInstancesRequest() {
    }

    @Override
    public ListInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
