package com.baidubce.services.as.model.asgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class AsGroupBatchRequest extends AbstractBceRequest {
    private List<String> groupIds;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
