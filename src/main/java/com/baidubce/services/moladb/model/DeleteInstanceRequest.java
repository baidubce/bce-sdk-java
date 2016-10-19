package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a DeleteInstance operation.
 */
public class DeleteInstanceRequest extends AbstractBceRequest {
    private String instanceName;

    /**
     * Constructs a new DeleteInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public DeleteInstanceRequest() {
    }
    
    /**
     * Constructs a new DeleteInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param instanceName The name of the instance to be deleted.
     */
    public DeleteInstanceRequest(String instanceName) {
        this.instanceName = instanceName;
    }
    
    /**
     * Set the name of the instance to be deleted.
     *
     * @param instanceName The name of the instance to be deleted.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public DeleteInstanceRequest withInstanceName(String instanceName) {
        this.setInstanceName(instanceName);
        return this;
    }

    /**
     * Set the name of the instance to be deleted.
     *
     * @param instanceName The name of the instance to be deleted.
     * 
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Get the name of the instance to be deleted.
     *
     * @return The name of the instance to be deleted.
     */
    public String getInstanceName() {
        return this.instanceName;
    }

    public DeleteInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
