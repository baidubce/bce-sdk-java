package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A MongoDB user, used as the element type of user list responses.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbUser {
    private String name;

    private String description;

    private String status;

    private List<MongodbRole> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MongodbRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MongodbRole> roles) {
        this.roles = roles;
    }
}
