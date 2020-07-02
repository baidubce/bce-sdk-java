/*
 * Copyright 2015-2020 Baidu, Inc.
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

package com.baidubce.services.media.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * The model which will be used to set source clip info in creating transcoding job
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SourceClip {
    /**
     * source bucket
     */
    private String bucket = null;
    /**
     * Bos key
     */
    private String sourceKey = null;

    /**
     * clip start time in seconds
     */
    private Integer startTimeInSecond = null;

    /**
     * clip duration time in seconds
     */
    private Integer durationInSecond  = null;

    /**
     * clip start time in milliseconds
     */
    private Integer startTimeInMillisecond = null;

    /**
     * clip duration time in milliseconds
     */
    private Integer durationInMillisecond = null;

    /**
     * the clip will enable watermarks
     */
    private Boolean enableLogo = null;

    /**
     * the clip will be the master clip
     */
    private Boolean asMasterClip;

    /**
     * the clip will enable delogo
     */
    private Boolean enableDelogo;

    /**
     * the clip will enable crop
     */
    private Boolean enableCrop;

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public SourceClip withSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
        return this;
    }

    public SourceClip withStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }


    public SourceClip withDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
        return this;
    }

    public SourceClip withStartTimeInMillisecond(Integer startTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
        return this;
    }

    public SourceClip withDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
        return this;
    }

    public SourceClip withEnableLogo(Boolean enableLogo) {
        this.enableLogo = enableLogo;
        return this;
    }

    public SourceClip withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public SourceClip withAsMasterClip(Boolean enableDelogo) {
        this.asMasterClip = asMasterClip;
        return this;
    }

    public SourceClip withEnableDelogo(Boolean enableDelogo) {
        this.enableDelogo = enableDelogo;
        return this;
    }

    public SourceClip withEnableCrop(Boolean enableCrop) {
        this.enableCrop = enableCrop;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Clip {\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    sourceKey: ").append(sourceKey).append("\n");
        sb.append("    startTimeInSecond: ").append(startTimeInSecond).append("\n");
        sb.append("    durationInSecond: ").append(durationInSecond).append("\n");
        sb.append("    startTimeInMillisecond: ").append(startTimeInMillisecond).append("\n");
        sb.append("    durationInMillisecond: ").append(durationInMillisecond).append("\n");
        sb.append("    enableLogo: ").append(enableLogo).append("\n");
        sb.append("    asMasterClip: ").append(asMasterClip).append("\n");
        sb.append("    enableDelogo: ").append(enableDelogo).append("\n");
        sb.append("    enableCrop: ").append(enableCrop).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
