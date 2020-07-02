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

package com.baidubce.services.cnap.model.releaserecord;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for create release record.
 */
public class CreateReleaseRecordRequest extends AbstractBceRequest {


    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The type of release record. eg: RdType.
     */
    private String type = "RdType";

    /**
     * The description of release record.
     */
    private String description = "";

    /**
     * The image info.
     */
    private List<ImageAndExpectVersionModel> images;

    /**
     * The config info.
     */
    private List<ConfigAndExpectVersionModel> configs = new LinkedList<ConfigAndExpectVersionModel>();

    /**
     * The task info.
     */
    private List<TaskReqModel> tasks;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImageAndExpectVersionModel> getImages() {
        return images;
    }

    public void setImages(List<ImageAndExpectVersionModel> images) {
        this.images = images;
    }

    public List<ConfigAndExpectVersionModel> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigAndExpectVersionModel> configs) {
        this.configs = configs;
    }

    public List<TaskReqModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskReqModel> tasks) {
        this.tasks = tasks;
    }

    public CreateReleaseRecordRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public CreateReleaseRecordRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public CreateReleaseRecordRequest withType(String type) {
        this.setType(type);
        return this;
    }

    public CreateReleaseRecordRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public CreateReleaseRecordRequest addImageAndExpectVersionModel(ImageAndExpectVersionModel model) {
        if (CollectionUtils.isEmpty(images)) {
            images = new LinkedList<ImageAndExpectVersionModel>();
        }
        images.add(model);
        return this;
    }

    public CreateReleaseRecordRequest addConfigAndExpectVersionModel(ConfigAndExpectVersionModel model) {
        if (CollectionUtils.isEmpty(configs)) {
            configs = new LinkedList<ConfigAndExpectVersionModel>();
        }
        configs.add(model);
        return this;
    }

    public CreateReleaseRecordRequest addTaskReqModel(TaskReqModel model) {
        if (CollectionUtils.isEmpty(tasks)) {
            tasks = new LinkedList<TaskReqModel>();
        }
        tasks.add(model);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateReleaseRecordRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
