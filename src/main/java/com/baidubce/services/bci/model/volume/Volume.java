/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.volume;

import java.util.List;
/**
 * The volume of container
 */
public class Volume {

    /**
     * The nfs of volume
     */
    private List<NfsVolume> nfs;

    /**
     * The empty dir of volume
     */
    private List<EmptyDirVolume> emptyDir;

    /**
     * The config file of volume
     */
    private List<ConfigFileVolume> configFile;

    /**
     * The constructor of Volume
     */
    public Volume() {

    }

    /**
     * The constructor of Volume
     * @param nfs The nfs of volume
     * @param emptyDir The empty dir of volume
     * @param configFile The config file of volume
     */
    public Volume(List<NfsVolume> nfs, List<EmptyDirVolume> emptyDir, List<ConfigFileVolume> configFile) {
        this.nfs = nfs;
        this.emptyDir = emptyDir;
        this.configFile = configFile;
    }

    public List<NfsVolume> getNfs() {
        return nfs;
    }

    public Volume setNfs(List<NfsVolume> nfs) {
        this.nfs = nfs;
        return this;
    }

    public List<EmptyDirVolume> getEmptyDir() {
        return emptyDir;
    }

    public Volume setEmptyDir(List<EmptyDirVolume> emptyDir) {
        this.emptyDir = emptyDir;
        return this;
    }

    public List<ConfigFileVolume> getConfigFile() {
        return configFile;
    }

    public Volume setConfigFile(List<ConfigFileVolume> configFile) {
        this.configFile = configFile;
        return this;
    }
}
