/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */

package com.baidubce.services.bmr.model;

/**
 * Represent a ldap application.
 * <p>
 * A ldap application can be configured with property of version.
 */
public class LdapApplicationConfig extends ApplicationConfig {

    private static final String LDAP_APPLICATION = "ldap";

    public LdapApplicationConfig() { this.setName(LDAP_APPLICATION); }

    /**
     * Configure the version of ldap.
     * The reference version is as follows:
     * <p>
     * image type |  image version | ldap version supported
     * hadoop    |    2.1.0       |    2.4.28
     *
     * @param version The version of ldap.
     *
     * @return LdapApplicationConfig
     */
    public LdapApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}