package com.baidubce.services.kafka.model.user;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * The response for deleting a user.
 */
@Data
public class DeleteUserResponse extends AbstractBceResponse {

    /**
     * The username of user which deleted.
     */
    private String username;
}