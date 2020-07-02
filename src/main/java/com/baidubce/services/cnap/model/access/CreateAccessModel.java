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

package com.baidubce.services.cnap.model.access;

/**
 * The model for create access.
 */
public class CreateAccessModel {

    public static final int CLUSTER_NETWORK = 1;
    public static final int VPC_NETWORK = 2;
    public static final int PUBLIC_NETWORK = 3;

    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The id of deploy group.
     */
    private String deployGroupID;

    /**
     * The name of access.
     */
    private String name;

    /**
     * The type of access.
     * 1 indicate access in cluster network.
     * 2 indicate access in vpc network.
     * 3 indicate access in public network.
     */
    private int type = PUBLIC_NETWORK;

    /**
     * The protocol of access.
     */
    private String protocol = "TCP";

    /**
     * The port of access.
     */
    private int port;

    /**
     * The port of listening.
     */
    private int targetPort;

    /**
     * The access type.
     */
    private String accessType = "BLB";

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getDeployGroupID() {
        return deployGroupID;
    }

    public void setDeployGroupID(String deployGroupID) {
        this.deployGroupID = deployGroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public CreateAccessModel withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public CreateAccessModel withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;
    }

    public CreateAccessModel withDeployGroupID(String deployGroupID) {
        this.setDeployGroupID(deployGroupID);
        return this;
    }

    public CreateAccessModel withName(String name) {
        this.setName(name);
        return this;
    }

    public CreateAccessModel withType(int type) {
        this.setType(type);
        return this;
    }

    public CreateAccessModel withProtocol(String protocol) {
        this.setProtocol(protocol);
        return this;
    }

    public CreateAccessModel withPort(int port) {
        this.setPort(port);
        return this;
    }

    public CreateAccessModel withTargetPort(int targetPort) {
        this.setTargetPort(targetPort);
        return this;
    }

    public CreateAccessModel withAccessType(String accessType) {
        this.setAccessType(accessType);
        return this;
    }



}
