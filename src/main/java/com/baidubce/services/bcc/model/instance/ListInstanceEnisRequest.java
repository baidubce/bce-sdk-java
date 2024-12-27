package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ListInstanceEnisRequest extends AbstractBceRequest {
    private String instanceId;

    public ListInstanceEnisRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }
    @Override
    public ListInstanceEnisRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
