package com.baidubce.services.subnet.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * The request model to query ipReserve list
 */
@Getter
@Setter
public class ListIpReserveRequest extends ListRequest {

    private String subnetId;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
