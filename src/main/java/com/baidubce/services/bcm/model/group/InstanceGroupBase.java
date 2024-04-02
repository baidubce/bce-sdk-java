package com.baidubce.services.bcm.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class InstanceGroupBase extends AbstractBceRequest {
    private String id;
    private String userId;

    @Override
    public InstanceGroupBase withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
