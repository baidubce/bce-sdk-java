package com.baidubce.services.scs.model.whitelist;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response of scs white list groups.
 */
public class ScsWhiteListGroupResponse extends AbstractBceResponse {

    private List<ScsWhiteListGroup> clusterIPGroups;

    public List<ScsWhiteListGroup> getClusterIPGroups() {
        return clusterIPGroups;
    }

    public void setClusterIPGroups(List<ScsWhiteListGroup> clusterIPGroups) {
        this.clusterIPGroups = clusterIPGroups;
    }
}
