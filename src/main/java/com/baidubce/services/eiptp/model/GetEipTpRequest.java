package com.baidubce.services.eiptp.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for getting eiptp detail.
 */
@Getter
@Setter
public class GetEipTpRequest extends AbstractBceRequest {

    /**
     * The eiptp id.
     */
    private String id;

    /**
     * Configure id for the request.
     *
     * @param id The id of GetEipTpRequest
     * @return GetEipTpRequest with the specific id
     */
    public GetEipTpRequest withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public GetEipTpRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
