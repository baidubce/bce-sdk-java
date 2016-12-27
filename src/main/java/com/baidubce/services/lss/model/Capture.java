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

package com.baidubce.services.lss.model;

public class Capture {
    private String mode = null;

    private Integer startTimeInSecond = null;

    private Integer endTimeInSecond = null;

    private Integer intervalInSecond = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Capture withMode(String mode) {
        this.mode = mode;
        return this;
    }

    public Integer getStartTimeInSecond() {
        return startTimeInSecond;
    }

    public void setStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
    }

    public Capture withStartTimeInSecond(Integer startTimeInSecond) {
        this.startTimeInSecond = startTimeInSecond;
        return this;
    }

    public Integer getEndTimeInSecond() {
        return endTimeInSecond;
    }

    public void setEndTimeInSecond(Integer endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
    }

    public Capture withEndTimeInSecond(Integer endTimeInSecond) {
        this.endTimeInSecond = endTimeInSecond;
        return this;
    }

    public Integer getIntervalInSecond() {
        return intervalInSecond;
    }

    public void setIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
    }

    public Capture withIntervalInSecond(Integer intervalInSecond) {
        this.intervalInSecond = intervalInSecond;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Capture {\n");
        sb.append("    mode: ").append(mode).append("\n");
        sb.append("    startTimeInSecond: ").append(startTimeInSecond).append("\n");
        sb.append("    endTimeInSecond: ").append(endTimeInSecond).append("\n");
        sb.append("    intervalInSecond: ").append(intervalInSecond).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
