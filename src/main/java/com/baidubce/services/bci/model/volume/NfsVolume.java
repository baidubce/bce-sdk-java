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
 * The nfs volume of container
 */
public class NfsVolume extends BaseVolume {

    /**
     * The server of nfs volume
     */
    private String server;

    /**
     * The path of nfs volume
     */
    private String path;

    /**
     * The read only of nfs volume
     */
    private Boolean readOnly = false;

    /**
     * The constructor of NfsVolume
     */
    public NfsVolume() {
        super();
    }

    /**
     * The constructor of NfsVolume
     * @param name The name of volume
     * @param server The server of nfs volume
     * @param path The path of nfs volume
     */
    public NfsVolume(String name, String server, String path) {
        super(name);
        this.server = server;
        this.path = path;
    }

    public String getServer() {
        return server;
    }

    public NfsVolume setServer(String server) {
        this.server = server;
        return this;
    }

    public String getPath() {
        return path;
    }

    public NfsVolume setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public NfsVolume setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }
}
