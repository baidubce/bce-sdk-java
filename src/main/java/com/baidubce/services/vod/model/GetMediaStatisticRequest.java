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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.Date;

/**
 * List the properties of all media resource managed by VOD service.
 */
public class GetMediaStatisticRequest extends AbstractBceRequest {

    private String mediaId;
    private Date startTime;
    private Date endTime;
    private boolean aggregate;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isAggregate() {
        return aggregate;
    }

    public void setAggregate(boolean aggregate) {
        this.aggregate = aggregate;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public GetMediaStatisticRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public GetMediaStatisticRequest withStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public GetMediaStatisticRequest withEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public GetMediaStatisticRequest withAggregate(boolean aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
