package com.baidubce.services.moladb.model;

import java.util.List;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a GetInstance operation.
 */
public class GetInstanceResponse extends AbstractBceResponse {
    private String description;
    private String name;
    private List<String> tableNames;
    
    /**
     * Constructs a new GetInstanceResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public GetInstanceResponse() {
    }

    /**
     * Get the description information for this instance.
     *  
     * @return The description information for this instance.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the name of this instance.
     * 
     * @return The name of this instance.
     */
    public String getInstanceName() {
        return name;
    }

    /**
     * Get all of the table names belong to this instance.
     * 
     * @return All of the table names belong to this instance, the type is in List<String> type.
     */
    public List<String> getTableNames() {
        return tableNames;
    }

    /**
     * Set the description information for this instance.
     * 
     * @param description The description information for this instance.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the name of the requested instance for response.
     * 
     * @param instanceName The name of the requested instance.
     */
    public void setInstanceName(String instanceName) {
        this.name = instanceName;
    }

    /**
     * Set the table names to the response tables set.
     * 
     * @param tableNames The names of tables to be added to the response tables set, the type is in List<String> type.
     */
    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }
}
