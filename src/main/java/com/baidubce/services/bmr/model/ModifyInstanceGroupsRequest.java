package com.baidubce.services.bmr.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Provide options for modifying instance groups of the target cluster.
 * <p>
 * The essential option is the ID of cluster, and the List of instanceGroups can be constructed by
 * calling the methods of ModifyInstanceGroupConfig.
 */
public class ModifyInstanceGroupsRequest extends AbstractBceRequest {
    private String clientToken;
    private String clusterId;
    private List<ModifyInstanceGroupConfig> instanceGroups;
    private List<String> deleteClientIds;

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public List<ModifyInstanceGroupConfig> getInstanceGroups() {
        return instanceGroups;
    }

    public void setInstanceGroups(List<ModifyInstanceGroupConfig> instanceGroups) {
        this.instanceGroups = instanceGroups;
    }

    public List<String> getDeleteClientIds() {
        return deleteClientIds;
    }

    public void setDeleteClientIds(List<String> deleteClientIds) {
        this.deleteClientIds = deleteClientIds;
    }

    /**
     * Configure the ID of the cluster.
     *
     * @param clusterId The ID of the cluster.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    /**
     * Configure the instance group to be modified.
     *
     * @param instanceGroup an instance group config to be added.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withInstanceGroup(ModifyInstanceGroupConfig instanceGroup) {
        if (this.instanceGroups == null) {
            this.instanceGroups = new ArrayList<ModifyInstanceGroupConfig>();
        }
        this.instanceGroups.add(instanceGroup);
        return this;
    }

    /**
     * Configure the instance groups to be modified. This method will replace the ModifyInstanceGroupsRequest
     * instance's instanceGroups by the @param instanceGroups totally, thus it should be
     * invoked ahead of withInstanceGroup method, if both of them are used for the same
     * ModifyInstanceGroupsRequest instance.
     *
     * @param instanceGroups an instance group config to be added.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withInstanceGroups(List<ModifyInstanceGroupConfig> instanceGroups) {
        this.setInstanceGroups(instanceGroups);
        return this;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withClientToken(String clientToken) {
        this.setClientToken(clientToken);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure clientIdList to be deleted for the request.
     *
     * @param deleteClientIds clientIds to be added.
     *
     * @return ModifyInstanceGroupsRequest
     */
    public ModifyInstanceGroupsRequest withDeleteClientIds(List<String> deleteClientIds) {
        this.setDeleteClientIds(deleteClientIds);
        return this;
    }

}
