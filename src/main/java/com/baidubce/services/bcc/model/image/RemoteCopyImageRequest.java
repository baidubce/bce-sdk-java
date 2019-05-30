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
package com.baidubce.services.bcc.model.image;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class RemoteCopyImageRequest extends AbstractBceRequest {

    /**
     * The id of image.
     */
    @JsonIgnore
    private String imageId;

    /**
     * The name of image.
     */
    private String name;

    /**
     * The id of destRegion.
     */
    private List<String> destRegion;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDestRegion() {
        return destRegion;
    }

    public void setDestRegion(List<String> destRegion) {
        this.destRegion = destRegion;
    }

    /**
     * Configure imageId for the request.
     *
     * @param imageId The id of image.
     * @return RemoteCopyImageRequest with imageId.
     */
    public RemoteCopyImageRequest withImageId(String imageId) {
        this.setImageId(imageId);
        return this;
    }

    /**
     * Configure name for the request.
     *
     * @param name The name of image.
     * @return RemoteCopyImageRequest with name.
     */
    public RemoteCopyImageRequest withname(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Configure imageId for the request.
     *
     * @param destRegion The id of destRegion.
     * @return RemoteCopyImageRequest with destRegion.
     */
    public RemoteCopyImageRequest withDestRegion(List<String> destRegion) {
        this.setDestRegion(destRegion);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return RemoteCopyImageRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
