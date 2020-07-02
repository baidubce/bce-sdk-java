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

package com.baidubce.services.cnap.model.deploygroup;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cnap.model.access.NewAccessModel;

/**
 * The request for create deploy group.
 */
public class CreateDeployGroupRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The access info of deploy group.
     */
    private List<NewAccessModel> access;

    /**
     * The deploy group info.
     */
    private NewDeployGroupModel deployGroup;

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

    public List<NewAccessModel> getAccess() {
        return access;
    }

    public void setAccess(List<NewAccessModel> access) {
        this.access = access;
    }

    public NewDeployGroupModel getDeployGroup() {
        return deployGroup;
    }

    public void setDeployGroup(NewDeployGroupModel deployGroup) {
        this.deployGroup = deployGroup;
    }

    public CreateDeployGroupRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public CreateDeployGroupRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public CreateDeployGroupRequest addAccess(NewAccessModel accessModel) {
        if (CollectionUtils.isEmpty(access)) {
            access = new LinkedList<NewAccessModel>();
        }
        this.access.add(accessModel);
        return this;
    }

    public CreateDeployGroupRequest withDeployGroup(NewDeployGroupModel deployGroup) {
        this.setDeployGroup(deployGroup);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateDeployGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
