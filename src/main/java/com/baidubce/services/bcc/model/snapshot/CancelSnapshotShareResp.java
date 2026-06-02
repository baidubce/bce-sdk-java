package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

public class CancelSnapshotShareResp extends AbstractBceResponse {

    /**
     * 源快照ID
     */
    private String sourceSnapshotId;

    /**
     * 共享快照ID
     */
    private String shareSnapshotId;

}
