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

public class PipelineConfig {
    private Integer capacity = null;
    private String notification = null;

    // public enum capacityEnum { };

    /**
     * 并发能力
     **/
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 通知
     **/
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public PipelineConfig withCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public PipelineConfig withNotification(String notification) {
        this.notification = notification;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PipelineConfig {\n");

        sb.append("    capacity: ").append(capacity).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
