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

package com.baidubce.services.cnap.model.workspace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for create workspace.
 */
public class CreateWorkspaceRequest extends AbstractBceRequest {

    /**
     * The workspace info.
     */
    private NewWorkspaceModel workspace;

    /**
     * The optional ids of cluster.
     */
    private List<String> clusterIDs = new ArrayList<String>();

    public NewWorkspaceModel getWorkspace() {
        return workspace;
    }

    public void setWorkspace(NewWorkspaceModel workspace) {
        this.workspace = workspace;
    }

    public List<String> getClusterIDs() {
        return clusterIDs;
    }

    public void setClusterIDs(List<String> clusterIDs) {
        this.clusterIDs = clusterIDs;
    }

    public CreateWorkspaceRequest withNewWorkspaceModel(NewWorkspaceModel newWorkspaceModel) {
        this.setWorkspace(newWorkspaceModel);
        return this;
    }

    public CreateWorkspaceRequest addClusterID(String clusterID) {
        if (CollectionUtils.isEmpty(clusterIDs)) {
            clusterIDs = new LinkedList<String>();
        }
        clusterIDs.add(clusterID);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateWorkspaceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
