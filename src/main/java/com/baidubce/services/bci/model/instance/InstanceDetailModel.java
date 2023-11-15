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

package com.baidubce.services.bci.model.instance;

import com.baidubce.services.bci.model.container.ContainerDetailModel;
import com.baidubce.services.bci.model.volume.Volume;
import com.baidubce.services.bci.model.vpc.SecurityGroupModel;
import com.baidubce.services.bci.model.vpc.SubnetModel;
import com.baidubce.services.bci.model.vpc.VpcModel;

import java.util.List;

/**
 * The instance of bci
 */
public class InstanceDetailModel extends InstanceModel {

    /**
     * The volume of instance
     */
    private Volume volume;

    /**
     * The containers of instance
     */
    private List<ContainerDetailModel> containers;

    /**
     * The initContainers of instance
     */
    private List<ContainerDetailModel> initContainers;

    /**
     * The securityGroups of instance
     */
    private List<SecurityGroupModel> securityGroups;

    /**
     * The vpc of instance
     */
    private VpcModel vpc;

    /**
     * The subnet of instance
     */
    private SubnetModel subnet;

    public Volume getVolume() {
        return volume;
    }

    public InstanceDetailModel setVolume(Volume volume) {
        this.volume = volume;
        return this;
    }

    public List<ContainerDetailModel> getContainers() {
        return containers;
    }

    public InstanceDetailModel setContainers(List<ContainerDetailModel> containers) {
        this.containers = containers;
        return this;
    }

    public List<ContainerDetailModel> getInitContainers() {
        return initContainers;
    }

    public InstanceDetailModel setInitContainers(List<ContainerDetailModel> initContainers) {
        this.initContainers = initContainers;
        return this;
    }

    public List<SecurityGroupModel> getSecurityGroups() {
        return securityGroups;
    }

    public InstanceDetailModel setSecurityGroups(List<SecurityGroupModel> securityGroups) {
        this.securityGroups = securityGroups;
        return this;
    }

    public VpcModel getVpc() {
        return vpc;
    }

    public InstanceDetailModel setVpc(VpcModel vpc) {
        this.vpc = vpc;
        return this;
    }

    public SubnetModel getSubnet() {
        return subnet;
    }

    public InstanceDetailModel setSubnet(SubnetModel subnet) {
        this.subnet = subnet;
        return this;
    }
}
