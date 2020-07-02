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

import org.apache.commons.collections.CollectionUtils;

/**
 * The model for container.
 */
public class ContainerModel {

    /**
     * The id of bap repository.
     */
    private String bapRepositoryID;

    /**
     * The name of image.
     */
    private String name;

    /**
     * The address of image.
     */
    private String image;

    /**
     * The command info.
     */
    private List<String> command;

    /**
     * The argument info.
     */
    private List<String> argus;

    /**
     * The environment info.
      */
    private List<EnvVarModel> env;

    /**
     * The lifecycle info.
     */
    private LifecycleModel lifecycle;

    /**
     * The liveness probe info.
     */
    private ProbeModel livenessProbe;

    /**
     * The readiness probe info.
     */
    private ProbeModel readinessProbe;

    /**
     * The resource requirement.
     */
    private ResourceRequirementsModel resources;

    public String getBapRepositoryID() {
        return bapRepositoryID;
    }

    public void setBapRepositoryID(String bapRepositoryID) {
        this.bapRepositoryID = bapRepositoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> command) {
        this.command = command;
    }

    public List<String> getArgus() {
        return argus;
    }

    public void setArgus(List<String> argus) {
        this.argus = argus;
    }

    public List<EnvVarModel> getEnv() {
        return env;
    }

    public void setEnv(List<EnvVarModel> env) {
        this.env = env;
    }

    public LifecycleModel getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(LifecycleModel lifecycle) {
        this.lifecycle = lifecycle;
    }

    public ProbeModel getLivenessProbe() {
        return livenessProbe;
    }

    public void setLivenessProbe(ProbeModel livenessProbe) {
        this.livenessProbe = livenessProbe;
    }

    public ProbeModel getReadinessProbe() {
        return readinessProbe;
    }

    public void setReadinessProbe(ProbeModel readinessProbe) {
        this.readinessProbe = readinessProbe;
    }

    public ResourceRequirementsModel getResources() {
        return resources;
    }

    public void setResources(ResourceRequirementsModel resources) {
        this.resources = resources;
    }

    public ContainerModel withBapRepositoryID(String bapRepositoryID) {
        this.setBapRepositoryID(bapRepositoryID);
        return this;
    }

    public ContainerModel withName(String name) {
        this.setName(name);
        return this;
    }

    public ContainerModel withImage(String image) {
        this.setImage(image);
        return this;
    }

    public ContainerModel addCommand(String command) {
        if (CollectionUtils.isEmpty(this.command)) {
            this.command = new LinkedList<String>();
        }
        this.command.add(command);
        return this;
    }

    public ContainerModel addArgu(String argu) {
        if (CollectionUtils.isEmpty(this.argus)) {
            this.argus = new LinkedList<String>();
        }
        this.argus.add(argu);
        return this;
    }

    public ContainerModel addEnv(EnvVarModel envVarModel) {
        if (CollectionUtils.isEmpty(this.env)) {
            this.env = new LinkedList<EnvVarModel>();
        }
        this.env.add(envVarModel);
        return this;
    }

    public ContainerModel withLifecycle(LifecycleModel lifecycle) {
        this.setLifecycle(lifecycle);
        return this;
    }

    public ContainerModel withLivenessProbe(ProbeModel livenessProbe) {
        this.setLivenessProbe(livenessProbe);
        return this;
    }

    public ContainerModel withReadinessProbe(ProbeModel readinessProbe) {
        this.setReadinessProbe(readinessProbe);
        return this;
    }

    public ContainerModel withResources(ResourceRequirementsModel resources) {
        this.setResources(resources);
        return this;
    }
}
