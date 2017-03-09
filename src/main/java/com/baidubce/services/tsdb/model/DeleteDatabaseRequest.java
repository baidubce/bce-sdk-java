package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for deleting database.
 */
public class DeleteDatabaseRequest extends AbstractBceRequest {

    private String databaseId;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public DeleteDatabaseRequest withDatabaseId(String databaseId) {
        this.databaseId = databaseId;
        return this;
    }

    @Override
    public DeleteDatabaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
