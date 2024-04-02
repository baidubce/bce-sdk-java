package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ListAlarmConfigForApplicationRequest extends AbstractBceRequest {
    private String userId;
    private String appName;
    private String alarmName;
    private Boolean actionEnabled;
    private String srcType;
    private String taskName;
    private int pageNo;
    private int pageSize = 10;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}