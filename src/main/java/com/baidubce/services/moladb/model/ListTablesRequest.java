package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a ListTables operation.
 */
public class ListTablesRequest extends AbstractBceRequest {
    /**
     * Constructs a new ListTablesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ListTablesRequest() {
    }

    public ListTablesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
