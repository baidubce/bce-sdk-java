package com.baidubce.services.et.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.ToString;

/**
 * The response for applying for a newly ET.
 */
@ToString
public class ApplyForEtResponse extends AbstractBceResponse {
    /**
     * ET id newly created
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
