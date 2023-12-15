package com.baidubce.services.et.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.ToString;

/**
 * The response for creating a newly ET tunnel
 */
@ToString
public class CreateEtChannelResponse extends AbstractBceResponse {
    /**
     * ET tunnel id newly created
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
