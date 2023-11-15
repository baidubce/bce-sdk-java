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

package com.baidubce.services.bci.model.container;

import com.baidubce.BceConstants;
import com.baidubce.services.bci.model.common.Environment;
import com.baidubce.services.bci.model.common.Port;
import com.baidubce.services.bci.model.volume.VolumeMount;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * The container of pod
 */
public class ContainerDetailModel {

    /**
     * The name of container
     */
    private String name;

    /**
     * The image of container
     */
    private String image;

    /**
     * The cpu count of container
     */
    private float cpu;

    /**
     * The memory of container
     */
    private float memory;

    /**
     * The gpu count of container
     */
    private float gpu;

    /**
     * The working directory of container
     */
    private String workingDir;

    /**
     * The image pull policy of container
     */
    private String imagePullPolicy;

    /**
     * The commands of container
     */
    private List<String> commands;

    /**
     * The arguments of container
     */
    private List<String> args;

    /**
     * The ports of container
     */
    private List<Port> ports;

    /**
     * The volume mounts of container
     */
    private List<VolumeMount> volumeMounts;

    /**
     * The environment of container
     */
    private List<Environment> envs;

    /**
     * The createTime of container
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp createTime;

    /**
     * The updateTime of container
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp updateTime;

    /**
     * The deleteTime of container
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp deleteTime;

    /**
     * The currentStatus of container
     */
    private ContainerStatus currentState;

    /**
     * The previousStatus of container
     */
    private ContainerStatus previousState;

    /**
     * The ready of container
     */
    private Boolean ready;

    /**
     * The restartCount of container
     */
    private int restartCount;

    /**
     * The ContainerStatus of container
     */
    public class ContainerStatus {

        /**
         * The state of container
         */
        private String state;

        /**
         * The reason of container
         */
        private String reason;

        /**
         * The message of container
         */
        private String message;

        /**
         * The startTime of container
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
        private Date startTime;

        /**
         * The finishTime of container
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
        private Date finishTime;

        /**
         * The detailStatus of container
         */
        private String detailStatus;

        /**
         * The exitCode of container
         */
        private int exitCode;

        public String getState() {
            return state;
        }

        public ContainerStatus setState(String state) {
            this.state = state;
            return this;
        }

        public String getReason() {
            return reason;
        }

        public ContainerStatus setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public ContainerStatus setMessage(String message) {
            this.message = message;
            return this;
        }

        public Date getStartTime() {
            return startTime;
        }

        public ContainerStatus setStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Date getFinishTime() {
            return finishTime;
        }

        public ContainerStatus setFinishTime(Date finishTime) {
            this.finishTime = finishTime;
            return this;
        }

        public String getDetailStatus() {
            return detailStatus;
        }

        public ContainerStatus setDetailStatus(String detailStatus) {
            this.detailStatus = detailStatus;
            return this;
        }

        public int getExitCode() {
            return exitCode;
        }

        public ContainerStatus setExitCode(int exitCode) {
            this.exitCode = exitCode;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public ContainerDetailModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ContainerDetailModel setImage(String image) {
        this.image = image;
        return this;
    }

    public float getCpu() {
        return cpu;
    }

    public ContainerDetailModel setCpu(float cpu) {
        this.cpu = cpu;
        return this;
    }

    public float getMemory() {
        return memory;
    }

    public ContainerDetailModel setMemory(float memory) {
        this.memory = memory;
        return this;
    }

    public float getGpu() {
        return gpu;
    }

    public ContainerDetailModel setGpu(float gpu) {
        this.gpu = gpu;
        return this;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public ContainerDetailModel setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
        return this;
    }

    public String getImagePullPolicy() {
        return imagePullPolicy;
    }

    public ContainerDetailModel setImagePullPolicy(String imagePullPolicy) {
        this.imagePullPolicy = imagePullPolicy;
        return this;
    }

    public List<String> getCommands() {
        return commands;
    }

    public ContainerDetailModel setCommands(List<String> commands) {
        this.commands = commands;
        return this;
    }

    public List<String> getArgs() {
        return args;
    }

    public ContainerDetailModel setArgs(List<String> args) {
        this.args = args;
        return this;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public ContainerDetailModel setPorts(List<Port> ports) {
        this.ports = ports;
        return this;
    }

    public List<VolumeMount> getVolumeMounts() {
        return volumeMounts;
    }

    public ContainerDetailModel setVolumeMounts(List<VolumeMount> volumeMounts) {
        this.volumeMounts = volumeMounts;
        return this;
    }

    public List<Environment> getEnvs() {
        return envs;
    }

    public ContainerDetailModel setEnvs(List<Environment> envs) {
        this.envs = envs;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public ContainerDetailModel setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public ContainerDetailModel setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public ContainerDetailModel setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
        return this;
    }

    public ContainerStatus getCurrentState() {
        return currentState;
    }

    public ContainerDetailModel setCurrentState(ContainerStatus currentState) {
        this.currentState = currentState;
        return this;
    }

    public ContainerStatus getPreviousState() {
        return previousState;
    }

    public ContainerDetailModel setPreviousState(ContainerStatus previousState) {
        this.previousState = previousState;
        return this;
    }

    public Boolean getReady() {
        return ready;
    }

    public ContainerDetailModel setReady(Boolean ready) {
        this.ready = ready;
        return this;
    }

    public int getRestartCount() {
        return restartCount;
    }

    public ContainerDetailModel setRestartCount(int restartCount) {
        this.restartCount = restartCount;
        return this;
    }
}
