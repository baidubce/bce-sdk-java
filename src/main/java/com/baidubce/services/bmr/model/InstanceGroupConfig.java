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
 * Represent the configuration for an instance group.
 * <p>
 * An instance group can be configured with name, type, instance type and instance count.
 * And the type, instance type and count are essential options.
 */
public class InstanceGroupConfig {
    private String name;
    private String type;
    private String instanceType;
    private int instanceCount;
    private int rootDiskSizeInGB;
    private String rootDiskMediumType;
    private List<CdsItem> cds = new ArrayList<CdsItem>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    /**
     * Configure the name of the instance group.
     *
     * @param name The name of the instance group.
     *
     * @return InstanceGroupConfig
     */
    public InstanceGroupConfig withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure the type of the instance group.
     * The type of instance group can be one of "Master", "Core" or "Task".
     *
     * @param type The type of the instance group.
     *
     * @return InstanceGroupConfig
     */
    public InstanceGroupConfig withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * Configure the instances'type in the target instance group.
     * The instance's type can be one of the following options:
     * "g.small":  2 CPU(cores)     8 GB mem     200 GB disk
     * "c.large":  8 CPU(cores)    32 GB mem     600 GB disk
     * "m.medium": 4 CPU(cores)    32 GB mem     400 GB disk
     * "s.medium": 4 CPU(cores)    16 GB mem    1000 GB disk
     *
     * @param instanceType The instances' type for the instance group.
     *
     * @return InstanceGroupConfig
     */
    public InstanceGroupConfig withInstanceType(String instanceType) {
        this.setInstanceType(instanceType);
        return this;
    }

    /**
     * Configure the instance count for the instance group.
     *
     * @param instanceCount The instance count for the instance group.
     *
     * @return InstanceGroupConfig
     */
    public InstanceGroupConfig withInstanceCount(int instanceCount) {
        this.setInstanceCount(instanceCount);
        return this;
    }

    public int getRootDiskSizeInGB() {
        return rootDiskSizeInGB;
    }

    public void setRootDiskSizeInGB(int rootDiskSizeInGB) {
        this.rootDiskSizeInGB = rootDiskSizeInGB;
    }

    public InstanceGroupConfig withRootDiskSizeInGB(int rootDiskSizeInGB) {
        this.rootDiskSizeInGB = rootDiskSizeInGB;
        return this;
    }

    public String getRootDiskMediumType() {
        return rootDiskMediumType;
    }

    public void setRootDiskMediumType(String rootDiskMediumType) {
        this.rootDiskMediumType = rootDiskMediumType;
    }

    public InstanceGroupConfig withRootDiskMediumType(String rootDiskMediumType) {
        this.rootDiskMediumType = rootDiskMediumType;
        return this;
    }

    public List<CdsItem> getCds() {
        return cds;
    }

    public void setCds(List<CdsItem> cds) {
        this.cds = cds;
    }

    public InstanceGroupConfig withCds(CdsItem cdsItem) {
        if (this.cds == null) {
            this.cds = new ArrayList<CdsItem>();
        }
        this.cds.add(cdsItem);
        return this;
    }
}
