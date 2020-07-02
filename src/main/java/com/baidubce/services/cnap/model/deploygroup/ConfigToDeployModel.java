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

package com.baidubce.services.cnap.model.deploygroup;

/**
 * The model for config to deploy.
 */
public class ConfigToDeployModel {

    /**
     * The id of config.
     */
    private String configID;

    /**
     * The id of config version.
     */
    private String configVersionID;

    /**
     * The absolute path of config file.
     */
    private String path;

    public String getConfigID() {
        return configID;
    }

    public void setConfigID(String configID) {
        this.configID = configID;
    }

    public String getConfigVersionID() {
        return configVersionID;
    }

    public void setConfigVersionID(String configVersionID) {
        this.configVersionID = configVersionID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ConfigToDeployModel withConfigID(String configID) {
        this.setConfigID(configID);
        return this;
    }

    public ConfigToDeployModel withConfigVersionID(String configVersionID) {
        this.setConfigID(configVersionID);
        return this;
    }

    public ConfigToDeployModel withPath(String path) {
        this.setPath(path);
        return this;
    }


}
