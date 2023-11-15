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

package com.baidubce.services.bci.model.securitycontext;

/**
 * The security context of container
 */
public class ContainerSecurityContext {

    // The capabilities to add/drop when running containers.
    // Defaults to the default set of capabilities granted by the container runtime.
    // Note that this field cannot be set when spec.os.name is windows.
    // +optional
    private Capabilities capabilities;

    // The UID to run the entrypoint of the container process.
    // Defaults to user specified in image metadata if unspecified.
    // May also be set in PodSecurityContext.  If set in both SecurityContext and
    // PodSecurityContext, the value specified in SecurityContext takes precedence.
    // Note that this field cannot be set when spec.os.name is windows.
    // +optional
    private Long runAsUser;

    // The GID to run the entrypoint of the container process.
    // Uses runtime default if unset.
    // May also be set in PodSecurityContext.  If set in both SecurityContext and
    // PodSecurityContext, the value specified in SecurityContext takes precedence.
    // Note that this field cannot be set when spec.os.name is windows.
    // +optional
    private Long runAsGroup;

    // Indicates that the container must run as a non-root user.
    // If true, the Kubelet will validate the image at runtime to ensure that it
    // does not run as UID 0 (root) and fail to start the container if it does.
    // If unset or false, no such validation will be performed.
    // May also be set in PodSecurityContext.  If set in both SecurityContext and
    // PodSecurityContext, the value specified in SecurityContext takes precedence.
    // +optional
    private Boolean runAsNonRoot;

    // Whether this container has a read-only root filesystem.
    // Default is false.
    // Note that this field cannot be set when spec.os.name is windows.
    // +optional
    private Boolean readOnlyRootFilesystem;

    /**
     * The constructor of ContainerSecurityContext
     */
    public ContainerSecurityContext() {

    }

    /**
     * The constructor of ContainerSecurityContext
     * @param capabilities The capabilities to add/drop when running containers.
     * @param runAsUser The UID to run the entrypoint of the container process.
     * @param runAsGroup The GID to run the entrypoint of the container process.
     * @param runAsNonRoot Indicates that the container must run as a non-root user.
     * @param readOnlyRootFilesystem Whether this container has a read-only root filesystem.
     */
    public ContainerSecurityContext(Capabilities capabilities, Long runAsUser, Long runAsGroup,
            Boolean runAsNonRoot, Boolean readOnlyRootFilesystem) {
        this.capabilities = capabilities;
        this.runAsUser = runAsUser;
        this.runAsGroup = runAsGroup;
        this.runAsNonRoot = runAsNonRoot;
        this.readOnlyRootFilesystem = readOnlyRootFilesystem;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public ContainerSecurityContext setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
        return this;
    }

    public Long getRunAsUser() {
        return runAsUser;
    }

    public ContainerSecurityContext setRunAsUser(Long runAsUser) {
        this.runAsUser = runAsUser;
        return this;
    }

    public Long getRunAsGroup() {
        return runAsGroup;
    }

    public ContainerSecurityContext setRunAsGroup(Long runAsGroup) {
        this.runAsGroup = runAsGroup;
        return this;
    }

    public Boolean getRunAsNonRoot() {
        return runAsNonRoot;
    }

    public ContainerSecurityContext setRunAsNonRoot(Boolean runAsNonRoot) {
        this.runAsNonRoot = runAsNonRoot;
        return this;
    }

    public Boolean getReadOnlyRootFilesystem() {
        return readOnlyRootFilesystem;
    }

    public ContainerSecurityContext setReadOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
        this.readOnlyRootFilesystem = readOnlyRootFilesystem;
        return this;
    }
}
