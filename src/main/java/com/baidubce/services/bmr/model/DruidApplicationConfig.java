/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bmr.model;

public class DruidApplicationConfig extends ApplicationConfig {
    private static final String DRUID_APPLICATION = "druid";
    private static final String METASTORE = "metastore";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String DATABASE = "database";
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    public DruidApplicationConfig() {
        this.setName(DRUID_APPLICATION);
    }

    /**
     * Configure the version of druid.
     * The reference version is as follows:
     * <p>
     * image type |  image version | druid version supported
     * hadoop    |    2.0.0       |    0.12.1
     *
     * @param version The version of druid.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    /**
     * Configure the metastore of druid Server。 By default, the inner metastore is used.
     * And you can also use MySQL by set the metastore to "mysql". If so, you must provide
     * other properties at the same time, including host and port for the MySQL service, the
     * database name, the username and password for accessing MySQL database.
     *
     * @param metastore The type of metastore. Use "default" or "mysql".
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withMetastore(String metastore) {
        this.addProperty(METASTORE, metastore);
        return this;
    }

    /**
     * Configure the host for MySQL service used by druid metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param host The host on which the MySQL service runs.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withHost(String host) {
        this.addProperty(HOST, host);
        return this;

    }

    /**
     * Configure the port for MySQL service used by druid metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param port The port on which the MySQL service listens.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withPort(int port) {
        this.addProperty(PORT, port);
        return this;
    }

    /**
     * Configure the database name for the druid metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param database The database's name for druid metastore.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withDatabase(String database) {
        this.addProperty(DATABASE, database);
        return this;
    }

    /**
     * Configure the username for accessing MySQL database.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param username The username for accessing MySQL database.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withUserName(String username) {
        this.addProperty(USERNAME, username);
        return this;
    }

    /**
     * Configure the password for accessing MySQL database.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param password The password for accessing MySQL database.
     *
     * @return DruidApplicationConfig
     */
    public DruidApplicationConfig withPassword(String password) {
        this.addProperty(PASSWORD, password);
        return this;
    }
}
