package com.baidubce.services.bcm.model.action;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;


@Data
public class ListActionsRequest extends AbstractBceRequest {
    private String userId;
    private String name;
    private String order;
    private String orderBy;
    private int pageNo;
    private int pageSize;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}