/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * List the properties of all media resource managed by VOD service.
 */
public class ListMediaResourceResponse extends AbstractBceResponse {

    /*
     * The list of all media resources managed by VOD service
     */
    List<MediaResource> media = new ArrayList<MediaResource>();

    public List<MediaResource> getMedia() {
        return media;
    }

    public void setMedia(List<MediaResource> media) {
        this.media = media;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ListMediaResourceResponse { \n");

        for (MediaResource mediaResource : getMedia()) {
            sb.append(mediaResource).append("\n");
        }
        sb.append("}\n");
        return sb.toString();

    }

}