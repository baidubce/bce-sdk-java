package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/14
 */
@Data
public class SiteAlarmConfigListRequest extends AbstractBceRequest {

    private String userId;
    private String taskId;
    private String aliasName;
    private boolean actionEnabled;
    private int pageNo;
    private int pageSize;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
