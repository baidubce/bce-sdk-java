package com.baidubce.services.as.model.task;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
public class DetachNodeRequest extends AbstractBceRequest {
    private String groupId;
    private List<String> nodes;

    @Override
    public DetachNodeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
