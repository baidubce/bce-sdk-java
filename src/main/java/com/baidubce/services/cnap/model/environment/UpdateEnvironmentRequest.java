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
package com.baidubce.services.cnap.model.environment;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for update environment.
 */
public class UpdateEnvironmentRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of environment.
     */
    private String environmentID;

    /**
     * The description of environment.
     */
    private String description;

    /**
     * The cluster id of environment.
     */
    private String clusterID;

    /**
     * The region of env.
     */
    private String region;

    /**
     * The flag which indicates ignore underlay error.
     */
    private boolean ignoreUnderlayError = false;

    /**
     * The flag which indicates skip delete underlay.
     */
    private boolean skipDeleteUnderlay = false;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean getIgnoreUnderlayError() {
        return ignoreUnderlayError;
    }

    public void setIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.ignoreUnderlayError = ignoreUnderlayError;
    }

    public boolean getSkipDeleteUnderlay() {
        return skipDeleteUnderlay;
    }

    public void setSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.skipDeleteUnderlay = skipDeleteUnderlay;
    }

    public String getEnvironmentID() {
        return environmentID;
    }

    public void setEnvironmentID(String environmentID) {
        this.environmentID = environmentID;
    }

    public UpdateEnvironmentRequest withIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.setIgnoreUnderlayError(ignoreUnderlayError);
        return this;
    }

    public UpdateEnvironmentRequest withSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.setSkipDeleteUnderlay(skipDeleteUnderlay);
        return this;
    }

    public UpdateEnvironmentRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public UpdateEnvironmentRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public UpdateEnvironmentRequest withClusterID(String clusterID) {
        this.setClusterID(clusterID);
        return this;
    }

    public UpdateEnvironmentRequest withRegion(String region) {
        this.setRegion(region);
        return this;
    }


    public UpdateEnvironmentRequest withEnvironmentID(String environmentID) {
        this.setEnvironmentID(environmentID);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public UpdateEnvironmentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
