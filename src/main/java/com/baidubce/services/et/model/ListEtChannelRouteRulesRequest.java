package com.baidubce.services.et.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

import lombok.ToString;

/**
 * The request for listing ET channel route rules.
 */
@ToString
public class ListEtChannelRouteRulesRequest extends ListRequest {
    /**
     * ET id
     */
    private String etId;

    /**
     * ET channel id
     */
    private String etChannelId;

    /**
     * The target network segment
     */
    private String destAddress;

    public String getEtId() {
        return etId;
    }

    public void setEtId(String etId) {
        this.etId = etId;
    }

    public String getEtChannelId() {
        return etChannelId;
    }

    public void setEtChannelId(String etChannelId) {
        this.etChannelId = etChannelId;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
