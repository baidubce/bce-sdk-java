package com.baidubce.services.kafka.model.user;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * The response for listing all of the available specifications of user.
 */
@Data
public class ListUserResponse extends AbstractBceResponse {

    /**
     * List of user info.
     */
    private List<User> users;
}