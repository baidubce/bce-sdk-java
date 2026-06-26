package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

public class ScsSyncGroupCreateResponse extends AbstractBceResponse {
    private String syncGroupShowId;

    public String getSyncGroupShowId() {
        return syncGroupShowId;
    }

    public void setSyncGroupShowId(String syncGroupShowId) {
        this.syncGroupShowId = syncGroupShowId;
    }
}
