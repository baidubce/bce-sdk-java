/*
 * Copyright 2015 Baidu, Inc.
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

public class SourceClip {
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

    public Integer getStartTimeInSecond() {
        return startTimeInSecond;
    }

    public void setStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
    }

    public SourceClip withStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }

    public Integer getDurationInSecond() {
        return durationInSecond;
    }

    public void setDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    public SourceClip withDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
        return this;
    }

    public Integer getStartTimeInMillisecond() {
        return startTimeInMillisecond;
    }

    public void setStartTimeInMillisecond(Integer startTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
    }

    public SourceClip withStartTimeInMillisecond(Integer startTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
        return this;
    }

    /**
     * 片段的持续时间，单位毫秒
     **/
    public Integer getDurationInMillisecond() {
        return durationInMillisecond;
    }

    public void setDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
    }

    public SourceClip withDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
        return this;
    }

    public Boolean getEnableLogo() {
        return enableLogo;
    }

    public void setEnableLogo(Boolean enableLogo) {
        this.enableLogo = enableLogo;
    }

    public SourceClip withEnableLogo(Boolean enableLogo) {
        this.enableLogo = enableLogo;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Clip {\n");
        sb.append("    sourceKey: ").append(sourceKey).append("\n");
        sb.append("    startTimeInSecond: ").append(startTimeInSecond).append("\n");
        sb.append("    durationInSecond: ").append(durationInSecond).append("\n");
        sb.append("    startTimeInMillisecond: ").append(startTimeInMillisecond).append("\n");
        sb.append("    durationInMillisecond: ").append(durationInMillisecond).append("\n");
        sb.append("    enableLogo: ").append(enableLogo).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
