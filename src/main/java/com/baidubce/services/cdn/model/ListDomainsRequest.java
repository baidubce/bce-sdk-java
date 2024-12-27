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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Request object for listing all the domains owned by a user. 
 */
public class ListDomainsRequest extends AbstractBceRequest {
    private String marker;

    /**
     * 可选
     * 支持IP或域名源站筛选，多个源站用‘,’分隔。
     */
    private String origin;
    
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
     * Configure the marker for the query request.
     * The marker marks the starting point for the query.
     * 
     * @param marker
     * @return returns this object
     */
    public ListDomainsRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public ListDomainsRequest withOrigin(String origin) {
        this.setOrigin(origin);
        return this;
    }

    public ListDomainsRequest addOrigin(String origin) {
        if (this.origin == null || this.origin.isEmpty()) {
            this.setOrigin(origin);
        } else {
            this.setOrigin(this.origin + "," + origin);
        }
        return this;
    }

    /**
     * (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public ListDomainsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
