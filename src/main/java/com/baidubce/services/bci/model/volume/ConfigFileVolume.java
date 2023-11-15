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
 * The config file volume of container
 */
public class ConfigFileVolume extends BaseVolume {

    /**
     * The config files of volume
     */
    private List<ConfigFileDetail> configFiles;

    /**
     * The default mode of volume
     */
    private Integer defaultMode;

    /**
     * The constructor of ConfigFileVolume
     */
    public ConfigFileVolume() {
        super();
    }

    /**
     * The constructor of ConfigFileVolume
     * @param name The name of volume
     * @param configFiles The config files of volume
     * @param defaultMode The default mode of volume
     */
    public ConfigFileVolume(String name, List<ConfigFileDetail> configFiles, Integer defaultMode) {
        super(name);
        this.configFiles = configFiles;
        this.defaultMode = defaultMode;
    }

    public List<ConfigFileDetail> getConfigFiles() {
        return configFiles;
    }

    public ConfigFileVolume setConfigFiles(List<ConfigFileDetail> configFiles) {
        this.configFiles = configFiles;
        return this;
    }

    public Integer getDefaultMode() {
        return defaultMode;
    }

    public ConfigFileVolume setDefaultMode(Integer defaultMode) {
        this.defaultMode = defaultMode;
        return this;
    }
}