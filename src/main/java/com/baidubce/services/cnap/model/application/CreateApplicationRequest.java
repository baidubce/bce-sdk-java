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

package com.baidubce.services.cnap.model.application;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cnap.model.access.NewAccessModel;
import com.baidubce.services.cnap.model.deploygroup.NewDeployGroupModel;

/**
 * The request for create app.
 */
public class CreateApplicationRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The access info.
     */
    private List<NewAccessModel> access;

    /**
     * The application info.
     */
    private ApplicationModel application;

    /**
     * The group info.
     */
    private NewDeployGroupModel deployGroup;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public List<NewAccessModel> getAccess() {
        return access;
    }

    public void setAccess(List<NewAccessModel> access) {
        this.access = access;
    }

    public ApplicationModel getApplication() {
        return application;
    }

    public void setApplication(ApplicationModel application) {
        this.application = application;
    }

    public NewDeployGroupModel getDeployGroup() {
        return deployGroup;
    }

    public void setDeployGroup(NewDeployGroupModel deployGroup) {
        this.deployGroup = deployGroup;
    }


    public CreateApplicationRequest addAccess(NewAccessModel accessModel) {
        if (CollectionUtils.isEmpty(access)) {
            access = new LinkedList<NewAccessModel>();
        }
        access.add(accessModel);
        return this;
    }

    public CreateApplicationRequest withApplication(ApplicationModel application) {
        this.setApplication(application);
        return this;
    }

    public CreateApplicationRequest withDeployGroup(NewDeployGroupModel deployGroup) {
        this.setDeployGroup(deployGroup);
        return this;
    }

    public CreateApplicationRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }


    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateApplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
