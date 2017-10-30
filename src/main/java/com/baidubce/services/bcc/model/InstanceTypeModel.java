/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The Specifications detail model for purchasing instance.
 * See more detail on
 * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.AE.9E.E4.BE.8B.E5.A5.97.E9.A4.90.E8.A7.84.E6.A0.BC">
 *     BCE API doc</a>
 */
public class InstanceTypeModel {

    /**
     * The general type defined by the Specification
     */
    private String type;

    /**
     * The name of the Specification which will be used when purchasing new instance.
     */
    private String name;

    /**
     * The total count of available CPU in this Specification.
     */
    private int cpuCount;

    /**
     * The total size of available memory in BG in this Specification.
     */
    private int memorySizeInGB;

    /**
     * The total size of available temporary volume in BG in this Specification.
     */
    private int localDiskSizeInGB;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getMemorySizeInGB() {
        return memorySizeInGB;
    }

    public void setMemorySizeInGB(int memorySizeInGB) {
        this.memorySizeInGB = memorySizeInGB;
    }

    public int getLocalDiskSizeInGB() {
        return localDiskSizeInGB;
    }

    public void setLocalDiskSizeInGB(int localDiskSizeInGB) {
        this.localDiskSizeInGB = localDiskSizeInGB;
    }

    @Override
    public String toString() {
        return "InstanceTypeModel{"
                + "type='" + type + '\''
                + ", name='" + name + '\''
                + ", cpuCount=" + cpuCount
                + ", memorySizeInGB=" + memorySizeInGB
                + ", localDiskSizeInGB=" + localDiskSizeInGB
                + '}';
    }
}
