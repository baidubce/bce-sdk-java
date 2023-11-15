package com.baidubce.services.kafka.model.user;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request for listing all of the available specifications of user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUsersRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    @JsonIgnore
    private String clusterId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListUserRequest with credentials.
     */
    @Override
    public ListUsersRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}