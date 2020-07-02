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

import java.util.LinkedList;
import java.util.List;

/**
 * The model for deploy group.
 */
public class NewDeployGroupModel {
    /**
     * The name of deploy group.
     */
    private String name;

    /**
     * The description of deploy group.
     */
    private String description = "";

    /**
     * The environment id of deploy group.
     */
    private String environmentID;

    /**
     * The count of replicas.
     */
    private int replicas = 1;

    /**
     * The type of deploy group.
     */
    private int type = DeployGroupType.NORMAL;

    /**
     * The type of deploy strategy.
     * RollingUpdate or Recreate.
     */
    private String deployStrategyType = "RollingUpdate";

    /**
     * The config of deploy group component.
     */
    private List<NewComponentModel> componentConf = new LinkedList<NewComponentModel>();

    /**
     * The config of deploy group.
     */
    private DeploySpecModel conf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvironmentID() {
        return environmentID;
    }

    public void setEnvironmentID(String environmentID) {
        this.environmentID = environmentID;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public String getDeployStrategyType() {
        return deployStrategyType;
    }

    public void setDeployStrategyType(String deployStrategyType) {
        this.deployStrategyType = deployStrategyType;
    }

    public List<NewComponentModel> getComponentConf() {
        return componentConf;
    }

    public void setComponentConf(List<NewComponentModel> componentConf) {
        this.componentConf = componentConf;
    }

    public DeploySpecModel getConf() {
        return conf;
    }

    public void setConf(DeploySpecModel conf) {
        this.conf = conf;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public NewDeployGroupModel withName(String name) {
        this.setName(name);
        return this;
    }

    public NewDeployGroupModel withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public NewDeployGroupModel withEnvironmentID(String environmentID) {
        this.setEnvironmentID(environmentID);
        return this;
    }

    public NewDeployGroupModel withReplicas(int replicas) {
        this.setReplicas(replicas);
        return this;
    }

    public NewDeployGroupModel withDeployStrategyType(String deployStrategyType) {
        this.setDeployStrategyType(deployStrategyType);
        return this;
    }

    public NewDeployGroupModel withType(int type) {
        this.setType(type);
        return this;
    }

    public NewDeployGroupModel addComponmentModel(NewComponentModel componentModel) {
        this.componentConf.add(componentModel);
        return this;
    }

    public NewDeployGroupModel withDeploySpecModel(DeploySpecModel conf) {
        this.setConf(conf);
        return this;
    }
}
