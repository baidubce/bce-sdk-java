package com.baidubce.services.gaiadb.model;

import java.util.List;

public class DatabaseListResponse extends GenericGaiadbResponse {
    private List<Database> databases;

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
