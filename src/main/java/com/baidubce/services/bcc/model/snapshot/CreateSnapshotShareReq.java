package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateSnapshotShareReq extends AbstractBceRequest {

    /**
     * 需要操作共享的快照id
     */
    private String snapshotId;

    /**
     * 共享的账号列表，最多支持100个百度云主账号
     */
    private List<String> accountIds;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
