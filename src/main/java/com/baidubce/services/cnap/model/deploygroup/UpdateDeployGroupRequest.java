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

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cnap.model.access.NewAccessModel;

/**
 * The request for update deploy group.
 */
public class UpdateDeployGroupRequest extends AbstractBceRequest {

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
     * The description of deploy group.
     */
    private String description;

    /**
     * The number of replicas.
     */
    private int replicas;

    /**
     * The deploy spec model conf.
     */
    private DeploySpecModel conf;

    /**
     * The component conf.
     */
    private List<ComponentModel> compnonentConf;

    /**
     * The access of deploy group.
     */
    private List<NewAccessModel> access;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public DeploySpecModel getConf() {
        return conf;
    }

    public void setConf(DeploySpecModel conf) {
        this.conf = conf;
    }

    public List<ComponentModel> getCompnonentConf() {
        return compnonentConf;
    }

    public void setCompnonentConf(List<ComponentModel> compnonentConf) {
        this.compnonentConf = compnonentConf;
    }

    public List<NewAccessModel> getAccess() {
        return access;
    }

    public void setAccess(List<NewAccessModel> access) {
        this.access = access;
    }

    public UpdateDeployGroupRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public UpdateDeployGroupRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public UpdateDeployGroupRequest withDeployGroupID(String deployGroupID) {
        this.setDeployGroupID(deployGroupID);
        return this;
    }

    public UpdateDeployGroupRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public UpdateDeployGroupRequest withReplicas(int replicas) {
        this.setReplicas(replicas);
        return this;
    }

    public UpdateDeployGroupRequest withConf(DeploySpecModel conf) {
        this.setConf(conf);
        return this;
    }

    public UpdateDeployGroupRequest withCompnonentConf(List<ComponentModel> compnonentConf) {
        this.setCompnonentConf(compnonentConf);
        return this;
    }

    public UpdateDeployGroupRequest withAccess(List<NewAccessModel> access) {
        this.setAccess(access);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public UpdateDeployGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
