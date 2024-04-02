package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class DeleteAlarmConfigForApplicationRequest extends AbstractBceRequest {
    private String userId;
    private String appName;
    private String alarmName;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}