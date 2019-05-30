package com.baidubce.services.tsdb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for getting rows from Tsdb.
 */
public class GetRowsWithSqlRequest extends AbstractBceRequest {

    /**
     * The sql to execute.
     */
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public GetRowsWithSqlRequest withSql(String sql) {
        this.sql = sql;
        return this;
    }

    @Override
    public GetRowsWithSqlRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
