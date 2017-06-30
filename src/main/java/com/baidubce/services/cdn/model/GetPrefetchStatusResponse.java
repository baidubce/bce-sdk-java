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

package com.baidubce.services.cdn.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yixing
 *
 */
public class GetPrefetchStatusResponse extends CdnResponse {
    private String marker;
    private String nextMarker;
    @JsonProperty
    private boolean isTruncated;
    private List<PrefetchStatus> details = new ArrayList<PrefetchStatus>();

    /**
     * @return marker
     */
    public String getMarker() {
        return marker;
    }

    /**
     * @param marker
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }

    /**
     * @return nextMarker
     */
    public String getNextMarker() {
        return nextMarker;
    }

    /**
     * @param nextMarker
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    /**
     * @return isTruncated
     */
    public boolean isTruncated() {
        return this.isTruncated;
    }

    /**
     * @return details
     */
    public List<PrefetchStatus> getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(List<PrefetchStatus> details) {
        this.details = details;
    }
}

