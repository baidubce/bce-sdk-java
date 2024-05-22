/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage.model;

/**
 * Performs List/Show Instance operation on a single row
 */
public class InstanceInfo {
    private String id;
    private String name;
    private String region;
    private String state;
    private String createTime;
    private String storageType;

    /**
     * Get the instance id
     *
     * @return The instance id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the instance id
     *
     * @param id The instance id set to instance info
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the instance name
     *
     * @return The instance name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the instance name
     *
     * @param name The instance name set to instance info
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the instance region
     *
     * @return The instance region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the instance region
     *
     * @param region The instance region set to instance info
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Get the instance state
     *
     * @return The instance state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the instance state
     *
     * @param state The instance state set to instance info
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the instance createTime
     *
     * @return The instance createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Set the instance createTime
     *
     * @param createTime The instanct createTime set to instance info
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Get the instance storageType
     *
     * @return The instance storageType
     */
    public String getStorageType() {
        return storageType;
    }

    /**
     * Set the instance storageType
     *
     * @param storageType The instance storageType set to instance info
     */
    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * Convert this object to string.
     *
     * @return the string represent this instnace info object.
     */
    @Override
    public String toString() {
        return "InstanceInfo [\n  id=" + id
                + ", \n  name=" + name
                + ", \n  region=" + region
                + ", \n  state=" + state
                + ", \n  storageType=" + storageType
                + ", \n  createTime=" + createTime + "\n]";

    }
}
/* vim: set expandtab ts=4 sw=4 sts=4 tw=100: */
