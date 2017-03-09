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

import java.util.Date;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class GetPurgeStatusRequest extends AbstractBceRequest {
    private String marker;
    private String id;
    private String url;
    
    private Date endTime;
    private Date startTime;
    
    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setEndTime(Date t) {
        this.endTime = t;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setStartTime(Date t) {
        this.startTime = t;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public GetPurgeStatusRequest withId(String id) {
        this.setId(id);
        return this;
    }

    public GetPurgeStatusRequest withUrl(String url) {
        this.setUrl(url);
        return this;
    }
    
    public GetPurgeStatusRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }
    
    public GetPurgeStatusRequest withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }
    
    public GetPurgeStatusRequest withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }
    
    @Override
    public GetPurgeStatusRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
