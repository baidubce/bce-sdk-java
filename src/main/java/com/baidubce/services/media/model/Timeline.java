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

public class Timeline {
    /**
     * start time in milliseconds
     */
    private Integer startTimeInMillisecond = null;

    /**
     * duration in milliseconds
     */
    private Integer durationInMillisecond = null;

    public Integer getStartTimeInMillisecond() {
        return startTimeInMillisecond;
    }

    public void setStartTimeInMillisecond(Integer startTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
    }

    public Timeline withStartTimeInMillisecond(Integer startTimeInMillisecond) {
        this.startTimeInMillisecond = startTimeInMillisecond;
        return this;
    }

    public Integer getDurationInMillisecond() {
        return durationInMillisecond;
    }

    public void setDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
    }

    public Timeline withDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Timeline {\n");

        sb.append("    startTimeInMillisecond: ").append(startTimeInMillisecond).append("\n");
        sb.append("    durationInMillisecond: ").append(durationInMillisecond).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
