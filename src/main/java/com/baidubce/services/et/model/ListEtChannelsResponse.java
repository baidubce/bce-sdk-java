package com.baidubce.services.et.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.ToString;

/**
 * The response for listing ET channels.
 */
@ToString
public class ListEtChannelsResponse extends AbstractBceResponse {
    private List<EtChannel> etChannels;

    public List<EtChannel> getEtChannels() {
        return etChannels;
    }

    public void setEtChannels(List<EtChannel> etChannels) {
        this.etChannels = etChannels;
    }
}
