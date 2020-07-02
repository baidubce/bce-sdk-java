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

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.BvwResponseMetadata;
import com.baidubce.services.bvw.model.common.UtcTime;
import com.baidubce.services.bvw.model.workflow.instance.InstanceModel;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Get media response.
 */
public class MediaGetResponse extends AbstractBceResponse {

    /**
     * The media id.
     */
    private String mediaId;
    /**
     * The media status.
     */
    private MediaStatus status;
    /**
     * The media title.
     */
    private String title;
    /**
     * The media description.
     */
    private String description;
    /**
     * The source bucket name.
     */
    private String sourceBucket;
    /**
     * The source key.
     */
    private String sourceKey;
    /**
     * The latest media processing instance.
     */
    private InstanceModel latestInstance;
    /**
     * The create time of media.
     */
    @UtcTime
    private Date createTime;

    public MediaGetResponse() {
        this.metadata = new BvwResponseMetadata();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        this.sourceBucket = sourceBucket;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public InstanceModel getLatestInstance() {
        return latestInstance;
    }

    public void setLatestInstance(InstanceModel latestInstance) {
        this.latestInstance = latestInstance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mediaId", mediaId)
                .append("status", status)
                .append("title", title)
                .append("description", description)
                .append("sourceBucket", sourceBucket)
                .append("sourceKey", sourceKey)
                .append("latestInstance", latestInstance)
                .append("createTime", createTime)
                .toString();
    }

}
