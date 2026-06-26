package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsSyncGroupModifyBnsNameRequest extends AbstractBceRequest {
    private String bnsGroup;

    public String getBnsGroup() {
        return bnsGroup;
    }

    public void setBnsGroup(String bnsGroup) {
        this.bnsGroup = bnsGroup;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
