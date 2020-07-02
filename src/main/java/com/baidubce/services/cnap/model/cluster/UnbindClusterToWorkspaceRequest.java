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

package com.baidubce.services.cnap.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for unbind cluster.
 */
public class UnbindClusterToWorkspaceRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The cluster id.
     */
    private String clusterID;

    /**
     * The flag indicates whether can ignore underlay error.
     */
    private boolean ignoreUnderlayError = false;

    /**
     * The flag indicates whether can skip delete underlay.
     */
    private boolean skipDeleteUnderlay = false;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getClusterID() {
        return clusterID;
    }

    public void setClusterID(String clusterID) {
        this.clusterID = clusterID;
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

    public UnbindClusterToWorkspaceRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public UnbindClusterToWorkspaceRequest withClusterID(String clusterID) {
        this.setClusterID(clusterID);
        return this;
    }

    public UnbindClusterToWorkspaceRequest withIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.setIgnoreUnderlayError(ignoreUnderlayError);
        return this;
    }

    public UnbindClusterToWorkspaceRequest withSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.setSkipDeleteUnderlay(skipDeleteUnderlay);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public UnbindClusterToWorkspaceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
