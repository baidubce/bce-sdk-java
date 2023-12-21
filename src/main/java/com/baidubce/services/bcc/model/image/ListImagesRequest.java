/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
import com.baidubce.model.ListRequest;

/**
 * The request for listing the images.
 */
public class ListImagesRequest extends ListRequest {
    /**
     * The optional parameter to filter image to list,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#ImageType">BCE API doc</a>
     */
    private String imageType;

    private String imageName;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * Configure the request with specified imageType.
     *
     * @param imageType The optional parameter to filter image to list, see more detail on
     *                  <a href = "https://bce.baidu.com/doc/BCC/API.html#ImageType">BCE API doc</a>
     * @return ListInstancesRequest with specified imageType.
     */
    public ListImagesRequest withImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    /**
     * Configure the request with specified marker.
     *
     * @param marker The optional parameter marker specified in the original request to specify
     *               where in the results to begin listing.
     * @return ListInstancesRequest with specified marker.
     */
    @Override
    public ListImagesRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    /**
     * Configure the request with specified maxKeys.
     *
     * @param maxKeys The optional parameter to specifies the max number of list result to return.
     *                The default value is 1000.
     * @return ListInstancesRequest with specified maxKeys.
     */
    @Override
    public ListImagesRequest withMaxKeys(int maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListImagesRequest with credentials.
     */
    @Override
    public ListImagesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Configure the request with specified imageName.
     *
     * @param imageName The optional parameter to filter image to list
     * @return ListInstancesRequest with specified imageName.
     */
    public ListImagesRequest withImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

}
