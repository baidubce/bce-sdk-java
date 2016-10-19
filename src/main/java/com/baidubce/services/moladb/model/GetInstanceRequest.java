package com.baidubce.services.moladb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represents the input of a GetInstance operation.
 */
public class GetInstanceRequest extends AbstractBceRequest {
    private String instanceName;
    
    /**
     * Constructs a new GetInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public GetInstanceRequest() {
    }

    /**
     * Constructs a new GetInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param instanceName The name of the instance to get from the account.
     */
    public GetInstanceRequest(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Set the name of the instance to get from the account.
     * 
     * @param instanceName The name of the instance to get from the account.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public GetInstanceRequest withInstanceName(String instanceName) {
        this.setInstanceName(instanceName);
        return this;
    }

    /**
     * Set the name of the instance to get from the account.
     * 
     * @param instanceName The name of the instance to get from the account.
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Get the name of the instance to get from the account.
     * 
     * @return The name of the instance to get from the account.
     */
    public String getInstanceName() {
        return this.instanceName;
    }

    public GetInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
