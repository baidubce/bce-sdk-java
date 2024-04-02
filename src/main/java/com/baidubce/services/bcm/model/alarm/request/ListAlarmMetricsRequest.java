package com.baidubce.services.bcm.model.alarm.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListAlarmMetricsRequest extends AbstractBceRequest {
    private String userId;
    private String scope;
    private String region;
    private String dimensions;
    private String type;
    private String locale;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
