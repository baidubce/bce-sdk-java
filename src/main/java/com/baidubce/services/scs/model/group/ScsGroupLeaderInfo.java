package com.baidubce.services.scs.model.group;

public class ScsGroupLeaderInfo {
    private String groupName;
    private String leaderRegion;
    private String leaderId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLeaderRegion() {
        return leaderRegion;
    }

    public void setLeaderRegion(String leaderRegion) {
        this.leaderRegion = leaderRegion;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }
}
