package com.baidubce.services.et.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

import lombok.ToString;

/**
 * The request for listing ET.
 */
@ToString
public class ListEtRequest extends ListRequest {
    /**
     * Express status. Its value range is ack-wait/accept/reject/building/pay-wait/established/stopped/deleted, which
     * respectively correspond to Applying/Application accepted/Application rejected/Constructing/Unpaid (the unpaid
     * port duration fee for the completed construction)/Available/Expired/Deleting after the expiry
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
