package com.baidubce.services.kafka.model.user;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * The response for reseting a password of a user.
 */
@Data
public class ResetUserPasswordResponse extends AbstractBceResponse {

    /**
     * The username of the user reseted.
     */
    private String username;
}
