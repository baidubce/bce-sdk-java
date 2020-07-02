/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

/*
 *  description of Cloud Disk
 */
public class CdsItem {
    private int sizeInGB;
    private String mediumType;

    public int getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    /**
     * @param sizeInGB disk size
     *
     * @return CdsItem
     */

    public CdsItem withSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
        return this;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    /**
     * @param mediumType for example ssd
     *
     * @return CdsItem
     */
    public CdsItem withMediumType(String mediumType) {
        this.mediumType = mediumType;
        return this;
    }
}
