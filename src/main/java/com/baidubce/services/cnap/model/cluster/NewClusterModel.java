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

/**
 * The model of cluster.
 */
public class NewClusterModel {
    /**
     * The region of cluster.
     */
    private String region = "bj";

    /**
     * The id of underlay cluster.
     */
    private String underlayClusterID;

    /**
     * The type of cluster.
     */
    private String type = "cce";

    /**
     * The description of cluster.
     */
    private String description;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUnderlayClusterID() {
        return underlayClusterID;
    }

    public void setUnderlayClusterID(String underlayClusterID) {
        this.underlayClusterID = underlayClusterID;
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

    public NewClusterModel withRegion(String region) {
        this.setRegion(region);
        return this;
    }

    public NewClusterModel withUnderlayClusterID(String underlayClusterID) {
        this.setUnderlayClusterID(underlayClusterID);
        return this;
    }

    public NewClusterModel withType(String type) {
        this.setType(type);
        return this;
    }

    public NewClusterModel withDescription(String description) {
        this.setDescription(description);
        return this;
    }
}
