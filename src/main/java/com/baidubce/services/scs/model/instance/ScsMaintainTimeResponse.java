package com.baidubce.services.scs.model.instance;

import com.baidubce.model.AbstractBceResponse;

public class ScsMaintainTimeResponse extends AbstractBceResponse {
    private String cacheClusterShowId;
    private ScsMaintainTime maintainTime;

    public String getCacheClusterShowId() {
        return cacheClusterShowId;
    }

    public void setCacheClusterShowId(String cacheClusterShowId) {
        this.cacheClusterShowId = cacheClusterShowId;
    }

    public ScsMaintainTime getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(ScsMaintainTime maintainTime) {
        this.maintainTime = maintainTime;
    }
}
