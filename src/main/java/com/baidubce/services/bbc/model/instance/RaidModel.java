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
package com.baidubce.services.bbc.model.instance;

/**
 * The detail of flavor raid
 */
public class RaidModel {
    /**
     * The id of raid
     */
    private String raidId;
    /**
     * The name of raid
     */
    private String raid;
    /**
     * The system swap default size
     */
    private int sysSwapSize;
    /**
     * The system root default size
     */
    private int sysRootSize;
    /**
     * The system /home default size
     */
    private int sysHomeSize;
    /**
     * The system disk total size
     */
    private int sysDiskSize;
    /**
     * The data disk total size
     */
    private int dataDiskSize;

    public String getRaidId() {
        return raidId;
    }

    public void setRaidId(String raidId) {
        this.raidId = raidId;
    }

    public String getRaid() {
        return raid;
    }

    public void setRaid(String raid) {
        this.raid = raid;
    }

    public int getSysSwapSize() {
        return sysSwapSize;
    }

    public void setSysSwapSize(int sysSwapSize) {
        this.sysSwapSize = sysSwapSize;
    }

    public int getSysRootSize() {
        return sysRootSize;
    }

    public void setSysRootSize(int sysRootSize) {
        this.sysRootSize = sysRootSize;
    }

    public int getSysHomeSize() {
        return sysHomeSize;
    }

    public void setSysHomeSize(int sysHomeSize) {
        this.sysHomeSize = sysHomeSize;
    }

    public int getSysDiskSize() {
        return sysDiskSize;
    }

    public void setSysDiskSize(int sysDiskSize) {
        this.sysDiskSize = sysDiskSize;
    }

    public int getDataDiskSize() {
        return dataDiskSize;
    }

    public void setDataDiskSize(int dataDiskSize) {
        this.dataDiskSize = dataDiskSize;
    }
}
