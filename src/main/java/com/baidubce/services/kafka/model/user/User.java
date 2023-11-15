package com.baidubce.services.kafka.model.user;

import lombok.Data;

/**
 * user detail info model.
 */
@Data
public class User {
    /**
     * user's name.
     */
    private String username;

    /**
     * the time for creating user.
     */
    private String createTime;
}