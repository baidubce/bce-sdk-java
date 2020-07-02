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

import java.util.List;

/**
 * The model for container config.
 */
public class ContainerConfigModel {
    /**
     * The name of container.
     */
    private String containerName;

    /**
     * The config to deploy list.
     */
    private List<ConfigToDeployModel> configToDeploys;

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public List<ConfigToDeployModel> getConfigToDeploys() {
        return configToDeploys;
    }

    public void setConfigToDeploys(
            List<ConfigToDeployModel> configToDeploys) {
        this.configToDeploys = configToDeploys;
    }
}
