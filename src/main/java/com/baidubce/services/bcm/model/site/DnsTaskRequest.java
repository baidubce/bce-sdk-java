package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class DnsTaskRequest extends BaseTaskRequest {

    private String server;
    private ResolveTypeEnum resolveType = ResolveTypeEnum.RECURSION;
    private String kidnapWhite;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
