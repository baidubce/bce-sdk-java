/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.TagModel;

import java.util.List;

/**
 * The request for creating a new instance snapshot(consistent snapshot group).
 */
public class CreateInstanceSnapshotRequest extends AbstractBceRequest {

    /**
     * The id of the instance which the instance snapshot will be created from.
     */
    private String instanceId;

    /**
     * The name for the instance snapshot that will be created.
     */
    private String name;

    /**
     * The optional parameter to describe the information of the new instance snapshot.
     */
    private String desc;

    /**
     * The optional parameter to specify the ids of volumes which will be included in the instance snapshot.
     * If it is not specified, all the volumes attached to the instance will be included.
     */
    private List<String> volumes;

    /**
     * The optional parameter to specify the retention days of the instance snapshot.
     */
    private Integer retentionInDays;

    /**
     * The optional parameter to specify the id of the resource group which the instance snapshot belongs to.
     */
    private String resGroupId;

    /**
     * The optional parameter to specify the tags bound to the instance snapshot.
     */
    private List<TagModel> tags;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Configure instanceId for the request.
     *
     * @param instanceId The id of the instance which the instance snapshot will be created from.
     * @return CreateInstanceSnapshotRequest with instanceId.
     */
    public CreateInstanceSnapshotRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Configure name for the request.
     *
     * @param name The name for the instance snapshot that will be created.
     * @return CreateInstanceSnapshotRequest with name.
     */
    public CreateInstanceSnapshotRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Configure desc for the request.
     *
     * @param desc The optional parameter to describe the information of the new instance snapshot.
     * @return CreateInstanceSnapshotRequest with desc.
     */
    public CreateInstanceSnapshotRequest withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    /**
     * Configure volumes for the request.
     *
     * @param volumes The ids of volumes which will be included in the instance snapshot.
     * @return CreateInstanceSnapshotRequest with volumes.
     */
    public CreateInstanceSnapshotRequest withVolumes(List<String> volumes) {
        this.volumes = volumes;
        return this;
    }

    public Integer getRetentionInDays() {
        return retentionInDays;
    }

    public void setRetentionInDays(Integer retentionInDays) {
        this.retentionInDays = retentionInDays;
    }

    /**
     * Configure retentionInDays for the request.
     *
     * @param retentionInDays The retention days of the instance snapshot.
     * @return CreateInstanceSnapshotRequest with retentionInDays.
     */
    public CreateInstanceSnapshotRequest withRetentionInDays(Integer retentionInDays) {
        this.retentionInDays = retentionInDays;
        return this;
    }

    public String getResGroupId() {
        return resGroupId;
    }

    public void setResGroupId(String resGroupId) {
        this.resGroupId = resGroupId;
    }

    /**
     * Configure resGroupId for the request.
     *
     * @param resGroupId The id of the resource group which the instance snapshot belongs to.
     * @return CreateInstanceSnapshotRequest with resGroupId.
     */
    public CreateInstanceSnapshotRequest withResGroupId(String resGroupId) {
        this.resGroupId = resGroupId;
        return this;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    /**
     * Configure tags for the request.
     *
     * @param tags The tags bound to the instance snapshot.
     * @return CreateInstanceSnapshotRequest with tags.
     */
    public CreateInstanceSnapshotRequest withTags(List<TagModel> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateInstanceSnapshotRequest with credentials.
     */
    @Override
    public CreateInstanceSnapshotRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
