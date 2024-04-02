package com.baidubce.services.bcm.model.action;


import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class DeleteActionRequest extends AbstractBceRequest {
    private String userId;
    private String name;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}