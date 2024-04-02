package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

/**
 * create by pangyangyang on 2023/12/14
 */
@Data
public class DeleteSiteAlarmConfigRequest extends AbstractBceRequest {

    private String userId;
    private List<String> alarmNames;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
