package com.baidubce.services.vpn.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class SslVpnUser {

    /**
     * Username, consisting of uppercase and lowercase letters, numbers, and the special characters -_/.
     * Must start with a letter and have a length of 1-65 characters.
     */
    private String userName;

    /**
     * Password, 8-17 characters, must include English letters, numbers, and the following symbols: !@#$%^*(_.
     */
    private String password;


    private String description;
}
