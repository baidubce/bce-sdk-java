package com.baidubce.services.scs.model.backup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response of scs backup list.
 */
public class ScsBackupListResponse extends AbstractBceResponse {

    private Integer totalCount;
    private List<ScsBackupInfo> backups;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ScsBackupInfo> getBackups() {
        return backups;
    }

    public void setBackups(List<ScsBackupInfo> backups) {
        this.backups = backups;
    }
}
