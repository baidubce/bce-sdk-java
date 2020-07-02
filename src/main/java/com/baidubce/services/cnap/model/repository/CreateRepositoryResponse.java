
package com.baidubce.services.cnap.model.repository;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for create repository.
 */
public class CreateRepositoryResponse extends AbstractBceResponse {

    /**
     * The endpoint of repository.
     */
    private String endpoint;

    /**
     * The flag which indicates repository is secure.
     */
    private boolean isSecure;

    /**
     * The flag which indicates repository is private.
     */
    private boolean isPrivate;

    /**
     * The name of repository.
     */
    private String name;

    /**
     * The repository password.
     */
    private String password;

    /**
     * The type of repository.
     */
    private int type;

    /**
     * The repository username.
     */
    private String username;

    /**
     * The id of repository.
     */
    private String resourceID;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(boolean isSecure) {
        this.isSecure = isSecure;
    }

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }
}
