package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInstanceCreateRequest extends AbstractBceRequest {
    @NonNull
    private String userId;

    @NonNull
    private String appName;

    private List<HostInstanceInfo> hostList;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
