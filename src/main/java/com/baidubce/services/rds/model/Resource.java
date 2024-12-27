package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {
    private String instanceId;
    private List<RdsTag> tags;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Resource(String instanceId, List<RdsTag> tags) {
        this.instanceId = instanceId;
        this.tags = tags;
    }

    public List<RdsTag> getTags() {
        return tags;
    }

    public void setTags(List<RdsTag> tags) {
        this.tags = tags;
    }
}
