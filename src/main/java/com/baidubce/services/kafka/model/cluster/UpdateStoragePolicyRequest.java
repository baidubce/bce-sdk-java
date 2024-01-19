package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class UpdateStoragePolicyRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean storagePolicyEnabled;

    private StoragePolicy storagePolicy;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public UpdateStoragePolicyRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public UpdateStoragePolicyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
