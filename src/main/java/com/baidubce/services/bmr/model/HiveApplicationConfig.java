/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bmr.model;

/**
 * Represent a Hive application.
 *
 * A Hive application can be configured with properties such as:
 * version, meta-store, host, port, database, username, password
 */
public class HiveApplicationConfig extends ApplicationConfig {
    private static final String HIVE_APPLICATION = "hive";
    private static final String METASTORE = "metastore";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String DATABASE = "database";
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    public HiveApplicationConfig() {
        this.setName(HIVE_APPLICATION);
    }

    /**
     * Configure the version of Hive.
     * The reference version is as follows:
     *
     *     image type |  image version | hive version supported
     *      hadoop    |    0.1.0       |    0.13.0
     *      hadoop    |    0.2.0       |    0.14.0
     *
     * @param version The version of Hive.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }

    /**
     * Configure the metastore of Hive Server。 By default, the inner metastore is used.
     * And you can also use MySQL by set the metastore to "mysql". If so, you must provide
     * other properties at the same time, including host and port for the MySQL service, the
     * database name, the username and password for accessing MySQL database.
     *
     * @param metastore The type of metastore. Use "default" or "mysql".
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withMetastore(String metastore) {
        this.addProperty(METASTORE, metastore);
        return this;
    }

    /**
     * Configure the host for MySQL service used by Hive metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param host The host on which the MySQL service runs.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withHost(String host) {
        this.addProperty(HOST, host);
        return this;

    }

    /**
     * Configure the port for MySQL service used by Hive metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param port The port on which the MySQL service listens.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withPort(int port) {
        this.addProperty(PORT, port);
        return this;
    }

    /**
     * Configure the database name for the Hive metastore.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param database The database's name for Hive metastore.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withDatabase(String database) {
        this.addProperty(DATABASE, database);
        return this;
    }

    /**
     * Configure the username for accessing MySQL database.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param username The username for accessing MySQL database.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withUserName(String username) {
        this.addProperty(USERNAME, username);
        return this;
    }

    /**
     * Configure the password for accessing MySQL database.
     * This configuration is needed only if the metastore is "mysql".
     *
     * @param password The password for accessing MySQL database.
     * @return HiveApplicationConfig
     */
    public HiveApplicationConfig withPassword(String password) {
        this.addProperty(PASSWORD, password);
        return this;
    }
}
