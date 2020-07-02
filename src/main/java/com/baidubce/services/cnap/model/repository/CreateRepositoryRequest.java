/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.repository;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for create repository.
 */
public class CreateRepositoryRequest extends AbstractBceRequest {

    /**
     * The endpoint of repository.
     * The endpoint of Baidu Yun repository: hub.baidubce.com
     */
    private String endpoint = "hub.baidubce.com";

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The name of repository.
     */
    private String name;

    /**
     * The type of repository.
     * 1 indicates Baidu Yun repository.
     * 2 indicates Docker Hub repository.
     * 3 indicates Custom repository.
     */
    private int type = 1;

    /**
     * The flag which indicates repository is secure.
     */
    private boolean isSecure = true;

    /**
     * The flag which indicates repository is private.
     */
    private boolean isPrivate = true;

    /**
     * The repository username.
     */
    private String username;

    /**
     * The repository password.
     */
    private String password;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getIsSecure() {
        return isSecure;
    }

    public void setIsSecure(boolean isSecure) {
        this.isSecure = isSecure;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public CreateRepositoryRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public CreateRepositoryRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public CreateRepositoryRequest withType(int type) {
        this.setType(type);
        return this;
    }

    public CreateRepositoryRequest withSecure(boolean isSecure) {
        this.setIsSecure(isSecure);
        return this;
    }

    public CreateRepositoryRequest withPriviate(boolean priviate) {
        this.setIsPrivate(priviate);
        return this;
    }

    public CreateRepositoryRequest withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public CreateRepositoryRequest withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public CreateRepositoryRequest withEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateRepositoryRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
