package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for listing database.
 */
public class ListDatabaseRequest extends AbstractBceRequest {

    @Override
    public ListDatabaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
