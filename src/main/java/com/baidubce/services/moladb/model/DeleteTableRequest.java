package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a DeleteTable operation.
 */
public class DeleteTableRequest extends AbstractBceRequest {
    private String tableName;
    
    /**
     * Constructs a new DeleteTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public DeleteTableRequest() {
    }

    /**
     * Constructs a new DeleteTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param tableName The name of the table to be deleted.
     */
    public DeleteTableRequest(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Set the name of the table to be deleted.
     * 
     * @param tableName The name of the table to be deleted.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public DeleteTableRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Set the name of the table to be deleted.
     * 
     * @param tableName The name of the table to be deleted.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the name of the table to be deleted.
     * 
     * @return The name of the table to be deleted.
     */
    public String getTableName() {
        return this.tableName;
    }

    public DeleteTableRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
