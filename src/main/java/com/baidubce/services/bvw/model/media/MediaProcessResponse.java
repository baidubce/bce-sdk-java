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
package com.baidubce.services.bvw.model.media;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Process media response.
 */
public class MediaProcessResponse extends AbstractBceResponse {

    /**
     * The process media id
     */
    private String mediaId;
    /**
     * The instance of process media and using workflow.
     */
    private String instanceId;

    public MediaProcessResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mediaId", mediaId)
                .append("instanceId", instanceId)
                .toString();
    }

}
