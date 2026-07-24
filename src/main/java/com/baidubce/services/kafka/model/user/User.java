package com.baidubce.services.kafka.model.user;

import lombok.Data;

import java.util.Set;

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

    /**
     * SASL mechanisms enabled for this user (e.g. SCRAM-SHA-256, SCRAM-SHA-512, PLAIN).
     */
    private Set<String> saslMechanisms;
}