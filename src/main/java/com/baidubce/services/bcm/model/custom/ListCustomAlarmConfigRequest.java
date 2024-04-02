package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListCustomAlarmConfigRequest extends AbstractBceRequest {
    private String userId;
    private String alarmName;
    private String namespace;
    private Boolean actionEnabled;
    private int pageNo;
    private int pageSize;
    @Override
    public ListCustomAlarmConfigRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
