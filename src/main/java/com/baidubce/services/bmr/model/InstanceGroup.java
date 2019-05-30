/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * instanceGroup is the group of instance which is configured identically
 */
public class InstanceGroup {
    private String id;
    private String instanceType;
    private String name;
    private String type;
    private int requestedInstanceCount;
    private int rootDiskSizeInGB;
    private String rootDiskMediumType;
    private List<CdsItem> cds = new ArrayList<CdsItem>();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setRequestedInstanceCount(int requestedInstanceCount) {
        this.requestedInstanceCount = requestedInstanceCount;
    }

    public int getRequestedInstanceCount() {
        return this.requestedInstanceCount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public int getRootDiskSizeInGB() {
        return rootDiskSizeInGB;
    }

    public void setRootDiskSizeInGB(int rootDiskSizeInGB) {
        this.rootDiskSizeInGB = rootDiskSizeInGB;
    }

    public String getRootDiskMediumType() {
        return rootDiskMediumType;
    }

    public void setRootDiskMediumType(String rootDiskMediumType) {
        this.rootDiskMediumType = rootDiskMediumType;
    }

    public List<CdsItem> getCds() {
        return cds;
    }

    public void setCds(List<CdsItem> cds) {
        this.cds = cds;
    }
}
