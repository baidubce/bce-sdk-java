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

package com.baidubce.services.cnap.model.releaserecord;

public class ImageAndExpectVersionModel {

    /**
     * The address of updated image.
     */
    private String image;

    /**
     * The version 0f update image.
     */
    private String expectImageVersion;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExpectImageVersion() {
        return expectImageVersion;
    }

    public void setExpectImageVersion(String expectImageVersion) {
        this.expectImageVersion = expectImageVersion;
    }

    public ImageAndExpectVersionModel withImage(String image) {
        this.setImage(image);
        return this;
    }

    public ImageAndExpectVersionModel withExpectImageVersion(String expectImageVersion) {
        this.setExpectImageVersion(expectImageVersion);
        return this;
    }
}
