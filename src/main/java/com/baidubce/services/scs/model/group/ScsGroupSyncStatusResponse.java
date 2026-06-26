package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsGroupSyncStatusResponse extends AbstractBceResponse {
    private List<FollowerInfo> followers;

    public static class FollowerInfo {
        private String clusterShowId;
        private String syncStatus;
        private Integer maxOffset;
        private Integer lag;

        public String getClusterShowId() {
            return clusterShowId;
        }

        public void setClusterShowId(String clusterShowId) {
            this.clusterShowId = clusterShowId;
        }

        public String getSyncStatus() {
            return syncStatus;
        }

        public void setSyncStatus(String syncStatus) {
            this.syncStatus = syncStatus;
        }

        public Integer getMaxOffset() {
            return maxOffset;
        }

        public void setMaxOffset(Integer maxOffset) {
            this.maxOffset = maxOffset;
        }

        public Integer getLag() {
            return lag;
        }

        public void setLag(Integer lag) {
            this.lag = lag;
        }
    }
}
