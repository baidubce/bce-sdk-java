package com.baidubce.services.moladb.model;

import java.util.List;
import com.baidubce.model.AbstractBceResponse;

/**
 * Represents the output of a ListInstance operation.
 */
public class ListInstancesResponse extends AbstractBceResponse {
    private List<String> instanceNames;
    
    /**
     * Constructs a new ListInstanceResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ListInstancesResponse() {
    }

    /**
     * Get the names of all instances got from server side.
     * 
     * @return The names of all instances got from server side.
     */
    public List<String> getInstanceNames() {
        return this.instanceNames;
    }
    
    /**
     * Set the names of all instances got from server side.
     * 
     * @param names The names of all instances got from server side.
     */
    public void setInstanceNames(List<String> names) {
        this.instanceNames = names; 
    }
}
