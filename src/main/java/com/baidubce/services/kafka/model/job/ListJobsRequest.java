package com.baidubce.services.kafka.model.job;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class ListJobsRequest extends ListRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String name;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListOperationsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListJobsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
