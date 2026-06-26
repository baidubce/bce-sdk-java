package com.baidubce.services.scs.model.group;

public class ScsGroupFollowerInfo {
    private String followerId;
    private String followerRegion;
    private String syncMaster;

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getFollowerRegion() {
        return followerRegion;
    }

    public void setFollowerRegion(String followerRegion) {
        this.followerRegion = followerRegion;
    }

    public String getSyncMaster() {
        return syncMaster;
    }

    public void setSyncMaster(String syncMaster) {
        this.syncMaster = syncMaster;
    }
}
