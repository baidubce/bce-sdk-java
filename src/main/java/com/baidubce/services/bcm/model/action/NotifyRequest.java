package com.baidubce.services.bcm.model.action;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotifyRequest extends AbstractBceRequest {
    private String name;
    private int pageNo;
    private int pageSize;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}