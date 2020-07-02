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

package com.baidubce.services.cnap.model.access;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for delete access.
 */
public class DeleteAccessRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The id of deploy group.
     */
    private String deployGroupID;

    /**
     * The id of access.
     */
    private String accessID;

    /**
     * The flag which indicates skip delete underlay.
     */
    private boolean skipDeleteUnderlay = false;

    /**
     * The flag which indicates ignore underlay error.
     */
    private boolean ignoreUnderlayError = false;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getDeployGroupID() {
        return deployGroupID;
    }

    public void setDeployGroupID(String deployGroupID) {
        this.deployGroupID = deployGroupID;
    }

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }

    public void setIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.ignoreUnderlayError = ignoreUnderlayError;
    }

    public boolean getIgnoreUnderlayError() {
        return ignoreUnderlayError;
    }

    public void setSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.skipDeleteUnderlay = skipDeleteUnderlay;
    }

    public boolean getSkipDeleteUnderlay() {
        return skipDeleteUnderlay;
    }

    public DeleteAccessRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public DeleteAccessRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public DeleteAccessRequest withDeployGroupID(String deployGroupID) {
        this.setDeployGroupID(deployGroupID);
        return this;
    }

    public DeleteAccessRequest withAccessID(String accessID) {
        this.setAccessID(accessID);
        return this;
    }

    public DeleteAccessRequest withSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.setSkipDeleteUnderlay(skipDeleteUnderlay);
        return this;
    }

    public DeleteAccessRequest withIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.setIgnoreUnderlayError(ignoreUnderlayError);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public DeleteAccessRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
