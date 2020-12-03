package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainOfflineModeSwitchResponse extends CdnResponse {
    private boolean OfflineMode;

    public boolean isOfflineMode() {
        return OfflineMode;
    }

    public void setOfflineMode(boolean offlineMode) {
        OfflineMode = offlineMode;
    }
}
