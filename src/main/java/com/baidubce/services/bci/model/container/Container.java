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

import com.baidubce.services.bci.model.common.Environment;
import com.baidubce.services.bci.model.common.Port;
import com.baidubce.services.bci.model.common.Probe;
import com.baidubce.services.bci.model.volume.VolumeMount;
import com.baidubce.services.bci.model.securitycontext.ContainerSecurityContext;

import java.util.List;

/**
 * The container of pod
 */
public class Container {

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
     * The gpu count of container
     */
    private float gpu;

    /**
     * The memory of container
     */
    private float memory;

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
     * The volume Mount of container
     */
    private List<VolumeMount> volumeMounts;

    /**
     * The Ports of container
     */
    private List<Port> ports;

    /**
     * The environment variables of container
     */
    private List<Environment> environmentVars;

    /**
     * The readiness probe of container
     */
    private Probe readinessProbe;

    /**
     * The liveness probe of container
     */
    private Probe livenessProbe;

    /**
     * The startup probe of container
     */
    private Probe startupProbe;

    /**
     * Whether the container should allocate a buffer for stdin in the container runtime.
     * If this is not set, reads from stdin in the container will always result in EOF.
     * Default is false.
     */
    private Boolean stdin = false;

    /**
     * Whether the container runtime should close the stdin channel after it has been opened by a single attach.
     * When stdin is true the stdin stream will remain open across multiple attach sessions.
     * If stdinOnce is set to true, stdin is opened on container start,
     * is empty until the first client attaches to stdin, and then remains open and accepts
     * data until the client disconnects, at which time stdin is closed and remains closed until the container is
     * restarted. If this flag is false, a container processes that reads from stdin will never receive an EOF.
     * Default is false
     */
    private Boolean stdinOnce = false;

    /**
     * Whether the container should allocate a TTY for itself, also requires 'stdin' to be true.
     * Default is false.
     */
    private Boolean tty = false;

    /**
     * The security context of container
     */
    private ContainerSecurityContext securityContext;

    public String getName() {
        return name;
    }

    public Container setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Container setImage(String image) {
        this.image = image;
        return this;
    }

    public float getCpu() {
        return cpu;
    }

    public Container setCpu(float cpu) {
        this.cpu = cpu;
        return this;
    }

    public float getGpu() {
        return gpu;
    }

    public Container setGpu(float gpu) {
        this.gpu = gpu;
        return this;
    }

    public float getMemory() {
        return memory;
    }

    public Container setMemory(float memory) {
        this.memory = memory;
        return this;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public Container setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
        return this;
    }

    public String getImagePullPolicy() {
        return imagePullPolicy;
    }

    public Container setImagePullPolicy(String imagePullPolicy) {
        this.imagePullPolicy = imagePullPolicy;
        return this;
    }

    public List<String> getCommands() {
        return commands;
    }

    public Container setCommands(List<String> commands) {
        this.commands = commands;
        return this;
    }

    public List<String> getArgs() {
        return args;
    }

    public Container setArgs(List<String> args) {
        this.args = args;
        return this;
    }

    public List<VolumeMount> getVolumeMounts() {
        return volumeMounts;
    }

    public Container setVolumeMounts(List<VolumeMount> volumeMounts) {
        this.volumeMounts = volumeMounts;
        return this;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public Container setPorts(List<Port> ports) {
        this.ports = ports;
        return this;
    }

    public List<Environment> getEnvironmentVars() {
        return environmentVars;
    }

    public Container setEnvironmentVars(List<Environment> environmentVars) {
        this.environmentVars = environmentVars;
        return this;
    }

    public Probe getReadinessProbe() {
        return readinessProbe;
    }

    public Container setReadinessProbe(Probe readinessProbe) {
        this.readinessProbe = readinessProbe;
        return this;
    }

    public Probe getLivenessProbe() {
        return livenessProbe;
    }

    public Container setLivenessProbe(Probe livenessProbe) {
        this.livenessProbe = livenessProbe;
        return this;
    }

    public Probe getStartupProbe() {
        return startupProbe;
    }

    public Container setStartupProbe(Probe startupProbe) {
        this.startupProbe = startupProbe;
        return this;
    }

    public Boolean getStdin() {
        return stdin;
    }

    public Container setStdin(Boolean stdin) {
        this.stdin = stdin;
        return this;
    }

    public Boolean getStdinOnce() {
        return stdinOnce;
    }

    public Container setStdinOnce(Boolean stdinOnce) {
        this.stdinOnce = stdinOnce;
        return this;
    }

    public Boolean getTty() {
        return tty;
    }

    public Container setTty(Boolean tty) {
        this.tty = tty;
        return this;
    }

    public ContainerSecurityContext getSecurityContext() {
        return securityContext;
    }

    public Container setSecurityContext(ContainerSecurityContext securityContext) {
        this.securityContext = securityContext;
        return this;
    }
}