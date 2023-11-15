package com.baidubce.services.kafka.model.user;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * The response for creating a newly user.
 */
@Data
public class CreateUserResponse extends AbstractBceResponse {

    /**
     * The username of user created.
     */
    private String username;
}