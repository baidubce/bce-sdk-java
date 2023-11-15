package com.baidubce.services.bec.model.resource;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for listing Bec Service Packages.
 */
@Data
public class ListBecServicePackagesRequest extends AbstractBceRequest {

    /**
     * Resource Package Type(vm,container,bm).
     */
    private String type;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetInstanceRequest with credentials.
     */
    public ListBecServicePackagesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
