/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.common;

/**
 * The credential of image registry
 */
public class ImageRegistryCredential {

    /**
     * The server of image registry
     */
    private String server;

    /**
     * The user name of image registry
     */
    private String userName;

    /**
     * The password of image registry
     */
    private String password;

    /**
     * Construct a new ImageRegistryCredential object.
     */
    public ImageRegistryCredential() {

    }

    /**
     * Construct a new ImageRegistryCredential object.
     *
     * @param server   The server of image registry
     * @param userName The user name of image registry
     * @param password The password of image registry
     */
    public ImageRegistryCredential(String server, String userName, String password) {
        this.server = server;
        this.userName = userName;
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public ImageRegistryCredential setServer(String server) {
        this.server = server;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ImageRegistryCredential setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ImageRegistryCredential setPassword(String password) {
        this.password = password;
        return this;
    }
}
