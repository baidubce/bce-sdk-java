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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for list deploy group by image.
 */
public class ListDeployGroupByImageRequest extends AbstractBceRequest {

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The images info, eg: nginx or nginx,mysql.
     */
    private String images;

    /**
     * The config id, eg: cm-QQSJFPQa or cm-CCSZEPQb
     */
    private String configIDs;

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getConfigIDs() {
        return configIDs;
    }

    public void setConfigIDs(String configIDs) {
        this.configIDs = configIDs;
    }

    public ListDeployGroupByImageRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public ListDeployGroupByImageRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public ListDeployGroupByImageRequest withImages(String images) {
        this.setImages(images);
        return this;
    }

    public ListDeployGroupByImageRequest withConfigIDs(String configIDs) {
        this.setConfigIDs(configIDs);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public ListDeployGroupByImageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
