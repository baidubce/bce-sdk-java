package com.baidubce.services.kafka.model.user;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for deleting a user.
 */
@Data
public class DeleteUserRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    /**
     * The username of user which to delete.
     */
    private String username;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return DeleteUserRequest with credentials.
     */
    @Override
    public DeleteUserRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}