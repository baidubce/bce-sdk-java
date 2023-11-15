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
 * The empty dir volume of container
 */
public class EmptyDirVolume extends BaseVolume {

    /**
     * The medium of volume
     */
    private String medium;

    /**
     * The size limit of volume
     */
    private Float sizeLimit;

    /**
     * The constructor of EmptyDirVolume
     */
    public EmptyDirVolume() {
        super();
    }

    /**
     * The constructor of EmptyDirVolume
     * @param name The name of volume
     * @param medium The medium of volume
     * @param sizeLimit The size limit of volume
     */
    public EmptyDirVolume(String name, String medium, Float sizeLimit) {
        super(name);
        this.medium = medium;
        this.sizeLimit = sizeLimit;
    }

    public String getMedium() {
        return medium;
    }

    public EmptyDirVolume setMedium(String medium) {
        this.medium = medium;
        return this;
    }

    public Float getSizeLimit() {
        return sizeLimit;
    }

    public EmptyDirVolume setSizeLimit(Float sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }
}
