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
package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;
import lombok.ToString;

/**
 * Gets a part of properties of specific media resource managed by VOD service.
 */
@Data
@ToString
public class GetPartMediaResourceRequest extends AbstractBceRequest {

    private String mediaId;
    private String scope;
    private String taskId;

    public GetPartMediaResourceRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public GetPartMediaResourceRequest withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public GetPartMediaResourceRequest withTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


}
