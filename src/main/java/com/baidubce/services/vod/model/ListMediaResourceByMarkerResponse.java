/*
 * Copyright 2016 Baidu, Inc.
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

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * List the properties of all media resource managed by VOD service.
 */
public class ListMediaResourceByMarkerResponse extends AbstractBceResponse {

    /*
     * The list of all media resources managed by VOD service
     */
    private List<MediaResource> media = new ArrayList<MediaResource>();

    /*
     * The request marker
     */
    private String marker;

    /*
     * The rest of media's marker, if isTruncated false, nextMarker=null
     */
    private String nextMarker;

    /*
     * whether there has more media, if true, means has more, use nextMarker to retrieve
     */
    private boolean isTruncated;


    public List<MediaResource> getMedia() {
        return media;
    }

    public void setMedia(List<MediaResource> media) {
        this.media = media;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(boolean truncated) {
        isTruncated = truncated;
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