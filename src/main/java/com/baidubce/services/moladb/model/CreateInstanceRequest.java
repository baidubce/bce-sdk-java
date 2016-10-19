package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a CreateInstance operation.
 */
public class CreateInstanceRequest extends AbstractBceRequest {
    private String instanceName;
    private String description;

    /**
     * Constructs a new CreateInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public CreateInstanceRequest() {
    }
    
    /**
     * Set the name of instance when creating an instance.
     * 
     * @param name The name of instance when creating an instance.
     */
    public void setInstanceName(String name) {
        this.instanceName = name;
    }

    /**
     * Set the name of instance when creating an instance.
     * 
     * @param name The name of instance when creating an instance.
     * @return A reference to this object so that method calls can be chained together.
     */
    public CreateInstanceRequest withInstanceName(String name) {
        this.setInstanceName(name);
        return this;
    }

    /**
     * Get the name of instance when creating an instance.
     * 
     * @return The name of instance when creating an instance.
     */
    public String getInstanceName() {
        return this.instanceName;
    }

    /**
     * Set the description information for the instance when creating an instance.
     * 
     * @param description The description information for the instance when creating an instance.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the description information for the instance when creating an instance.
     * 
     * @param description The description information for the instance when creating an instance.
     * @return A reference to this object so that method calls can be chained together.
     */
    public CreateInstanceRequest withDescription(String description) {
        this.setDescription(description);
        return this; 
    }
    
    /**
     * Get the description information for the instance when creating an instance.
     * 
     * @return The description information for the instance when creating an instance.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        Map<String, String> jsonObj = new HashMap<String, String>();
        jsonObj.put(MolaDbConstants.JSON_NAME, instanceName);
        jsonObj.put(MolaDbConstants.JSON_DESCRIPTION, description);
        String body = JsonUtils.toJsonString(jsonObj); 
        return body;
    }

    public CreateInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
