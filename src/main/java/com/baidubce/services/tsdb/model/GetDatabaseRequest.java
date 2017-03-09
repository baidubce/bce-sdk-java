package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for getting database.
 */
public class GetDatabaseRequest extends AbstractBceRequest {

    private String databaseId;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }


    public GetDatabaseRequest withDatabaseId(String databaseId) {
        this.databaseId = databaseId;
        return this;
    }

    @Override
    public GetDatabaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
