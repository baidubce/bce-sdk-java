package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSyncGroupStatusResponse extends AbstractBceResponse {
    private String syncGroupShowId;
    private List<SyncStatus> syncStatus;

    public String getSyncGroupShowId() {
        return syncGroupShowId;
    }

    public void setSyncGroupShowId(String syncGroupShowId) {
        this.syncGroupShowId = syncGroupShowId;
    }

    public List<SyncStatus> getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(List<SyncStatus> syncStatus) {
        this.syncStatus = syncStatus;
    }

    public static class SyncStatus {
        private String memberId;
        private String status;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
