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
 * The config file detail of container
 */
public class ConfigFileDetail {

    /**
     * The path of config file
     */
    private String path;

    /**
     * The file of config file
     */
    private String file;

    /**
     * The constructor of ConfigFileDetail
     */
    public ConfigFileDetail() {
        super();
    }

    /**
     * The constructor of ConfigFileDetail
     * @param path The path of config file
     * @param file The file of config file
     */
    public ConfigFileDetail(String path, String file) {
        this.path = path;
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public ConfigFileDetail setPath(String path) {
        this.path = path;
        return this;
    }

    public String getFile() {
        return file;
    }

    public ConfigFileDetail setFile(String file) {
        this.file = file;
        return this;
    }
}