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
    private Integer startTimeInSecond = null;

    private Integer durationInSecond  = null;

    private String sourceKey = null;

    /**
     * 片段的起始时间
     **/
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

    /**
     * 片段的持续时间
     **/
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

    /**
     * 片段地址
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Clip {\n");
        sb.append("    startTimeInSecond: ").append(startTimeInSecond)
                .append("\n");
        sb.append("    durationInSecond: ").append(durationInSecond)
                .append("\n");
        sb.append("    sourceKey: ").append(sourceKey)
                .append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
