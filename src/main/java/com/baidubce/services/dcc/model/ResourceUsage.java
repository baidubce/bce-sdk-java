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
package com.baidubce.services.dcc.model;

import java.util.List;

/**
 * dcc resource usage detail info model
 */
public class ResourceUsage {
    /**
     * The sum count of CPU within the dcc
     */
    private int cpuCount;

    /**
     * The free count of CPU within the dcc
     */
    private int freeCpuCount;

    /**
     * The total size of memory in GB for the dcc
     */
    private int memoryCapacityInGB;

    /**
     * The free size of memory in GB for the dcc
     */
    private int freeMemoryCapacityInGB;

    /**
     * The ephemeral disk info list
     */
    private List<EphemeralDisk> ephemeralDisks;

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getFreeCpuCount() {
        return freeCpuCount;
    }

    public void setFreeCpuCount(int freeCpuCount) {
        this.freeCpuCount = freeCpuCount;
    }

    public int getMemoryCapacityInGB() {
        return memoryCapacityInGB;
    }

    public void setMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
    }

    public int getFreeMemoryCapacityInGB() {
        return freeMemoryCapacityInGB;
    }

    public void setFreeMemoryCapacityInGB(int freeMemoryCapacityInGB) {
        this.freeMemoryCapacityInGB = freeMemoryCapacityInGB;
    }

    public List<EphemeralDisk> getEphemeralDisks() {
        return ephemeralDisks;
    }

    public void setEphemeralDisks(List<EphemeralDisk> ephemeralDisks) {
        this.ephemeralDisks = ephemeralDisks;
    }

    @Override
    public String toString() {
        return "ResourceUsage{"
                + "cpuCount=" + cpuCount
                + ", freeCpuCount=" + freeCpuCount
                + ", memoryCapacityInGB=" + memoryCapacityInGB
                + ", freeMemoryCapacityInGB=" + freeMemoryCapacityInGB
                + ", ephemeralDisks=" + ephemeralDisks
                + '}';
    }
}
