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

/**
 * The volume mount of container
 */
public class VolumeMount {

    /**
     * The name of volume
     */
    private String name;

    /**
     * The type of volume
     */
    private String type;

    /**
     * The mount path of volume
     */
    private String mountPath;

    /**
     * The read only of volume
     */
    private Boolean readOnly = false;

    /**
     * The constructor of VolumeMount
     */
    public VolumeMount() {

    }

    /**
     * The constructor of VolumeMount
     * @param name The name of volume
     * @param type The type of volume
     * @param mountPath The mount path of volume
     * @param readOnly The read only of volume
     */
    public VolumeMount(String name, String type, String mountPath, Boolean readOnly) {
        this.name = name;
        this.type = type;
        this.mountPath = mountPath;
        this.readOnly = readOnly;
    }

    public String getName() {
        return name;
    }

    public VolumeMount setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public VolumeMount setType(String type) {
        this.type = type;
        return this;
    }

    public String getMountPath() {
        return mountPath;
    }

    public VolumeMount setMountPath(String mountPath) {
        this.mountPath = mountPath;
        return this;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public VolumeMount setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }
}
