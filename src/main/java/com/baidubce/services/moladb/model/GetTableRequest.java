package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a GetTable operation.
 */
public class GetTableRequest extends AbstractBceRequest {
    private String tableName;
    
    /**
     * Constructs a new GetTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public GetTableRequest() {
    }

    /**
     * Constructs a new GetTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param tableName The name of the table from which to get information.
     */
    public GetTableRequest(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Set the name of the table for this object.
     *
     * @param tableName The name of the table to be set.
     * @return A reference to this object so that method calls can be chained together.
     */
    public GetTableRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Set the name of the table for this object.
     *
     * @param tableName The name of the table to be set.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Get the name of the table from this object.
     *
     * @return The name of the table.
     */
    public String getTableName() {
        return this.tableName;
    }

    public GetTableRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
