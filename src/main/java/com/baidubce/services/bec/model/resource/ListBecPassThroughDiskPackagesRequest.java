package com.baidubce.services.bec.model.resource;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for listing bec passThrough disk packages.
 */
public class ListBecPassThroughDiskPackagesRequest extends AbstractBceRequest {

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetInstanceRequest with credentials.
     */
    public ListBecPassThroughDiskPackagesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
