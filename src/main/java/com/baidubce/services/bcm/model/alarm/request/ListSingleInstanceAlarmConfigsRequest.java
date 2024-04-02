package com.baidubce.services.bcm.model.alarm.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListSingleInstanceAlarmConfigsRequest extends AbstractBceRequest {
    private String userId;
    private String scope;
    private String region;
    private String dimensions;
    private String order;
    private int pageNo;
    private int pageSize;
    private Boolean actionEnabled;
    private String alarmNamePrefix;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}