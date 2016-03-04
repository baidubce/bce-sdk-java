package com.baidubce.services.moladb.model;

import java.util.HashMap;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a UpdateTable operation.
 */
public class UpdateTableRequest extends AbstractBceRequest {
    private String tableName;
    private ProvisionedThroughput provisionedThroughput = null;

    /**
     * Constructs a new UpdateTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public UpdateTableRequest() {
    }
    
    /**
     * Constructs a new UpdateTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param name The name of table to be updated.
     */
    public UpdateTableRequest(String name) {
        this.tableName = name;
    }

    /**
     * Set the name of table for this object.
     *
     * @param tableName The name of table set for this object.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Set the name of table for this object.
     *
     * @param tableName The name of table set for this object.
     * @return A reference to this object so that method calls can be chained together.
     */
    public UpdateTableRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Get the name of table from this object.
     *
     * @return The name of table got from this object.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set provisioned throughput for updating table.
     *
     * @param provision The ProvisionedThroughput object used to set provisioned throughput for updating table.
     */
    public void setProvisionedThroughput(ProvisionedThroughput provision) {
        this.provisionedThroughput = provision;
    }

    /**
     * Set provisioned throughput for updating table.
     *
     * @param provision The ProvisionedThroughput object used to set provisioned throughput for updating table.
     * @return A reference to this object so that method calls can be chained together.
     */
    public UpdateTableRequest withProvisionedThroughput(ProvisionedThroughput provision) {
        this.setProvisionedThroughput(provision);
        return this;
    }

    /**
     * Get the ProvisionedThroughput object used to set provisioned throughput for updating table from this object.
     *
     * @return The ProvisionedThroughput object used to set provisioned throughput for updating table from this object.
     */
    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        if (this.provisionedThroughput == null) {
            throw new NullPointerException("provisionedThroughput throughput is null");
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put(MolaDbConstants.JSON_PROVISION_THROUGHPUT,
                   this.provisionedThroughput.toJsonObj());
        return JsonUtils.toJsonString(result);
    }

    public UpdateTableRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
