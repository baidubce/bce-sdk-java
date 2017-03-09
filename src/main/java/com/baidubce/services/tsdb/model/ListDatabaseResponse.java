package com.baidubce.services.tsdb.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the request for listing database.
 */
public class ListDatabaseResponse extends AbstractBceResponse {
    private List<GetDatabaseResponse> databases = new ArrayList<GetDatabaseResponse>();

    public List<GetDatabaseResponse> getDatabases() {
        return databases;
    }

    public void setDatabases(List<GetDatabaseResponse> databases) {
        this.databases = databases;
    }
}
