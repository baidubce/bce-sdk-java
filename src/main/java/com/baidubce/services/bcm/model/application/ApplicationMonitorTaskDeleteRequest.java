package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongjiaming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationMonitorTaskDeleteRequest extends AbstractBceRequest {
    private String userId;

    private String name;

    private String appName;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
