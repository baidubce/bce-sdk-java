package com.baidubce.services.bmr.model;

/**
 * Represent configuration for modify instance group operation.
 * <p>
 * The essential options are id and instance count.
 */
public class ModifyInstanceGroupConfig {
    private String id;

    private int instanceCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }
}
