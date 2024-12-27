package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetDatabaseListResponse extends AbstractBceResponse {
    private List<RdsDatabase> databases;

    public List<RdsDatabase> getDatabases() {
        return databases;
    }

    public void setDatabases(List<RdsDatabase> databases) {
        this.databases = databases;
    }
}
