package com.baidubce.services.scs.model.whitelist;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Request to manage scs white list group.
 */
public class ScsWhiteListGroupRequest extends AbstractBceRequest {

    private String instanceId;
    private String groupName;
    private String newGroupName;
    private List<String> clusterIpList;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }

    public List<String> getClusterIpList() {
        return clusterIpList;
    }

    public void setClusterIpList(List<String> clusterIpList) {
        this.clusterIpList = clusterIpList;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
