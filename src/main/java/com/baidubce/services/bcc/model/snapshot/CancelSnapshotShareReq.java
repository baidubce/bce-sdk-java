package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class CancelSnapshotShareReq extends AbstractBceRequest {

    /**
     * 共享方取消
     * 源快照ID
     */
    private String sourceSnapshotId;

    /**
     * 共享方取消需要传用户。为空，表示取消共享所有
     * 被取消共享的账号ID
     */
    private List<String> accountIds;

    /**
     * 接受方取消
     * 共享快照ID
     */
    private String shareSnapshotId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
