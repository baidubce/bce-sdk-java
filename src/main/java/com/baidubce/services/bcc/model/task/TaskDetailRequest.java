package com.baidubce.services.bcc.model.task;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class TaskDetailRequest extends AbstractBceRequest {

    private List<String> taskIds;

    private Integer maxKeys = 100;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
