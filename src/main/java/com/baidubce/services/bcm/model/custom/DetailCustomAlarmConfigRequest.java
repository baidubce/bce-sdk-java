package com.baidubce.services.bcm.model.custom;

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
public class DetailCustomAlarmConfigRequest extends AbstractBceRequest {
    private String userId;
    private String alarmName;
    private String namespace;
    @Override
    public DetailCustomAlarmConfigRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
