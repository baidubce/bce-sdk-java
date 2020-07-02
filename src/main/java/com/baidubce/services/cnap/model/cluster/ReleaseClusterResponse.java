package com.baidubce.services.cnap.model.cluster;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cnap.model.application.ResourceUsageModel;

/**
 * The response for release cluster.
 */
public class ReleaseClusterResponse extends AbstractBceResponse {
    /**
     * The name of cluster.
     */
    private String name;

    /**
     * The region of cluster.
     */
    private String region;

    /**
     * The id of cluster.
     */
    private String resourceID;

    /**
     * The description of cluster.
     */
    private String description;

    /**
     * The type of cluster.
     */
    private String type;

    /**
     * The id of underlay cluster.
     */
    private String underlayClusterID;

    /**
     * The virtual machine count of cluster.
     */
    private int slaveVmCount;

    /**
     * The status of cluster.
     */
    private String status;

    /**
     * The create time of cluster.
     */
    private Date createdAt;

    /**
     * The update time of cluster.
     */
    private Date updatedAt;

    /**
     * The resource usage of cluster.
     */
    private ResourceUsageModel resourceUsageModel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnderlayClusterID() {
        return underlayClusterID;
    }

    public void setUnderlayClusterID(String underlayClusterID) {
        this.underlayClusterID = underlayClusterID;
    }

    public int getSlaveVmCount() {
        return slaveVmCount;
    }

    public void setSlaveVmCount(int slaveVmCount) {
        this.slaveVmCount = slaveVmCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ResourceUsageModel getResourceUsageModel() {
        return resourceUsageModel;
    }

    public void setResourceUsageModel(ResourceUsageModel resourceUsageModel) {
        this.resourceUsageModel = resourceUsageModel;
    }
}
